import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * Servidor local del laboratorio de patrones.
 * Uso:  java Servidor.java      y abre http://localhost:8765
 * Sirve la página y compila/ejecuta el código Java que le envías (solo escucha en 127.0.0.1).
 */
public class Servidor {
    static final Path RAIZ = Paths.get(".").toAbsolutePath().normalize();
    static final int LIMITE_SALIDA = 64 * 1024;
    static final Map<String, String> TIPOS = Map.of(
            "html", "text/html; charset=utf-8",
            "js", "text/javascript; charset=utf-8",
            "css", "text/css; charset=utf-8",
            "java", "text/plain; charset=utf-8",
            "txt", "text/plain; charset=utf-8",
            "json", "application/json; charset=utf-8");

    static String binJava;
    static String binJavac;

    public static void main(String[] args) throws Exception {
        int puerto = args.length > 0 ? Integer.parseInt(args[0]) : 8765;
        binJava = ProcessHandle.current().info().command().orElse("java");
        Path bin = Paths.get(binJava).getParent();
        Path javac = bin == null ? null : bin.resolve(binJava.endsWith(".exe") ? "javac.exe" : "javac");
        binJavac = javac != null && Files.exists(javac) ? javac.toString() : "javac";

        var hilos = Executors.newFixedThreadPool(4);
        // Escucha en IPv4 (127.0.0.1) y en IPv6 (::1): según el equipo, "localhost" puede resolverse a cualquiera
        int activos = 0;
        for (String ip : new String[]{"127.0.0.1", "::1"}) {
            try {
                HttpServer s = HttpServer.create(new InetSocketAddress(InetAddress.getByName(ip), puerto), 0);
                s.createContext("/api/run", Servidor::ejecutar);
                s.createContext("/api/test", Servidor::probar);
                s.createContext("/", Servidor::estatico);
                s.setExecutor(hilos);
                s.start();
                activos++;
            } catch (IOException e) {
                System.out.println("Aviso: no se pudo escuchar en " + ip + ":" + puerto + " (" + e.getMessage() + ")");
            }
        }
        if (activos == 0) {
            System.out.println("El puerto " + puerto + " está ocupado. ¿Ya está abierto el laboratorio? Prueba http://127.0.0.1:" + puerto);
            System.exit(1);
        }
        System.out.println("Laboratorio de patrones listo en http://localhost:" + puerto + "  (o http://127.0.0.1:" + puerto + ")");
        System.out.println("javac: " + binJavac);
    }

    static void estatico(HttpExchange ex) throws IOException {
        String ruta = URLDecoder.decode(ex.getRequestURI().getPath(), StandardCharsets.UTF_8);
        if (ruta.equals("/")) ruta = "/index.html";
        Path p = RAIZ.resolve(ruta.substring(1)).normalize();
        if (!p.startsWith(RAIZ) || !Files.isRegularFile(p)) {
            responder(ex, 404, "text/plain; charset=utf-8", "No encontrado".getBytes(StandardCharsets.UTF_8));
            return;
        }
        String nombre = p.getFileName().toString();
        String ext = nombre.contains(".") ? nombre.substring(nombre.lastIndexOf('.') + 1) : "";
        ex.getResponseHeaders().set("Cache-Control", "no-store");
        responder(ex, 200, TIPOS.getOrDefault(ext, "application/octet-stream"), Files.readAllBytes(p));
    }

    static void ejecutar(HttpExchange ex) throws IOException {
        if (!"POST".equals(ex.getRequestMethod())) {
            responder(ex, 405, "text/plain", new byte[0]);
            return;
        }
        String codigo = new String(ex.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
        Path dir = Files.createTempDirectory("patrones");
        try {
            Files.writeString(dir.resolve("Main.java"), codigo, StandardCharsets.UTF_8);
            long t0 = System.currentTimeMillis();
            Resultado c = proceso(dir, 20, binJavac, "-encoding", "UTF-8", "-nowarn", "-d", ".", "Main.java");
            if (c.salida.isBlank() && c.codigo == 0 && !Files.exists(dir.resolve("Main.class"))) {
                c = new Resultado("error: el archivo debe declarar 'public class Main' con un método main.", 1, false);
            }
            if (c.codigo != 0 || c.timeout) {
                responder(ex, 200, "application/json; charset=utf-8",
                        json("compilacion", c, System.currentTimeMillis() - t0).getBytes(StandardCharsets.UTF_8));
                return;
            }
            long t1 = System.currentTimeMillis();
            Resultado r = proceso(dir, 10, binJava, "-Dstdout.encoding=UTF-8", "-Dstderr.encoding=UTF-8",
                    "-Dfile.encoding=UTF-8", "-Xss8m", "-cp", ".", "Main");
            responder(ex, 200, "application/json; charset=utf-8",
                    json("ejecucion", r, System.currentTimeMillis() - t1).getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            responder(ex, 500, "application/json; charset=utf-8",
                    json("servidor", new Resultado("Error del servidor: " + e, 1, false), 0).getBytes(StandardCharsets.UTF_8));
        } finally {
            borrar(dir);
        }
    }

    /**
     * Modo CodeRunner: el cuerpo trae las clases del alumno + clases Prueba1..PruebaN (cada una con su main).
     * Se compila una vez y se ejecuta cada prueba en su propia JVM (así el estado estático no se comparte).
     * Parámetro de la URL: ?n=N
     */
    static void probar(HttpExchange ex) throws IOException {
        if (!"POST".equals(ex.getRequestMethod())) {
            responder(ex, 405, "text/plain", new byte[0]);
            return;
        }
        String q = ex.getRequestURI().getQuery();
        int n = 0;
        if (q != null && q.startsWith("n=")) {
            try { n = Math.min(50, Integer.parseInt(q.substring(2))); } catch (NumberFormatException ignored) { }
        }
        String codigo = new String(ex.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
        Path dir = Files.createTempDirectory("patrones");
        try {
            Files.writeString(dir.resolve("Main.java"), codigo, StandardCharsets.UTF_8);
            long t0 = System.currentTimeMillis();
            Resultado c = proceso(dir, 20, binJavac, "-encoding", "UTF-8", "-nowarn", "-d", ".", "Main.java");
            if (c.codigo != 0 || c.timeout) {
                responder(ex, 200, "application/json; charset=utf-8",
                        json("compilacion", c, System.currentTimeMillis() - t0).getBytes(StandardCharsets.UTF_8));
                return;
            }
            StringBuilder sb = new StringBuilder("{\"fase\":\"pruebas\",\"resultados\":[");
            for (int i = 1; i <= n; i++) {
                long t1 = System.currentTimeMillis();
                Resultado r = proceso(dir, 10, binJava, "-Dstdout.encoding=UTF-8", "-Dstderr.encoding=UTF-8",
                        "-Dfile.encoding=UTF-8", "-Xshare:auto", "-XX:TieredStopAtLevel=1", "-cp", ".", "Prueba" + i);
                if (i > 1) sb.append(',');
                sb.append(json("ejecucion", r, System.currentTimeMillis() - t1));
            }
            sb.append("]}");
            responder(ex, 200, "application/json; charset=utf-8", sb.toString().getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            responder(ex, 500, "application/json; charset=utf-8",
                    json("servidor", new Resultado("Error del servidor: " + e, 1, false), 0).getBytes(StandardCharsets.UTF_8));
        } finally {
            borrar(dir);
        }
    }

    record Resultado(String salida, int codigo, boolean timeout) {}

    static Resultado proceso(Path dir, int segundos, String... cmd) throws Exception {
        ProcessBuilder pb = new ProcessBuilder(List.of(cmd)).directory(dir.toFile()).redirectErrorStream(true);
        pb.environment().put("JAVA_TOOL_OPTIONS", "");
        Process pr = pb.start();
        pr.getOutputStream().close();
        CompletableFuture<String> lectura = CompletableFuture.supplyAsync(() -> leer(pr.getInputStream()));
        boolean termino = pr.waitFor(segundos, TimeUnit.SECONDS);
        if (!termino) pr.destroyForcibly().waitFor(2, TimeUnit.SECONDS);
        String salida;
        try {
            salida = lectura.get(2, TimeUnit.SECONDS);
        } catch (Exception e) {
            salida = "";
        }
        salida = salida.replace("Picked up JAVA_TOOL_OPTIONS: \n", "").replace("Picked up JAVA_TOOL_OPTIONS: \r\n", "");
        return new Resultado(salida, termino ? pr.exitValue() : -1, !termino);
    }

    static String leer(InputStream in) {
        try (in) {
            byte[] buf = in.readNBytes(LIMITE_SALIDA);
            String s = new String(buf, StandardCharsets.UTF_8);
            if (in.read() != -1) s += "\n... (salida recortada a 64 KB)";
            in.transferTo(OutputStream.nullOutputStream());
            return s;
        } catch (IOException e) {
            return "";
        }
    }

    static String json(String fase, Resultado r, long ms) {
        return "{\"fase\":\"" + fase + "\",\"codigo\":" + r.codigo + ",\"timeout\":" + r.timeout
                + ",\"ms\":" + ms + ",\"salida\":" + texto(r.salida) + "}";
    }

    static String texto(String s) {
        StringBuilder b = new StringBuilder("\"");
        for (char ch : s.replace("\r\n", "\n").toCharArray()) {
            switch (ch) {
                case '"' -> b.append("\\\"");
                case '\\' -> b.append("\\\\");
                case '\n' -> b.append("\\n");
                case '\r' -> b.append("\\r");
                case '\t' -> b.append("\\t");
                default -> {
                    if (ch < 0x20) b.append(String.format("\\u%04x", (int) ch));
                    else b.append(ch);
                }
            }
        }
        return b.append('"').toString();
    }

    static void responder(HttpExchange ex, int estado, String tipo, byte[] cuerpo) throws IOException {
        ex.getResponseHeaders().set("Content-Type", tipo);
        ex.sendResponseHeaders(estado, cuerpo.length == 0 ? -1 : cuerpo.length);
        if (cuerpo.length > 0) try (OutputStream os = ex.getResponseBody()) { os.write(cuerpo); }
        ex.close();
    }

    static void borrar(Path dir) {
        try (Stream<Path> w = Files.walk(dir)) {
            w.sorted(Comparator.reverseOrder()).forEach(p -> p.toFile().delete());
        } catch (IOException ignored) {
        }
    }
}
