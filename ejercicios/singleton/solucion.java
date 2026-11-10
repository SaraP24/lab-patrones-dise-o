import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Configuracion a = Configuracion.getInstancia();
        Configuracion b = Configuracion.getInstancia();
        a.set("entorno", "produccion");
        System.out.println("Mismo objeto: " + (a == b));
        System.out.println("entorno=" + b.get("entorno"));

        Set<Configuracion> vistas = Collections.synchronizedSet(Collections.newSetFromMap(new IdentityHashMap<>()));
        Thread[] hilos = new Thread[10];
        for (int i = 0; i < hilos.length; i++) {
            hilos[i] = new Thread(() -> vistas.add(Configuracion.getInstancia()));
            hilos[i].start();
        }
        for (Thread h : hilos) h.join();
        System.out.println("Instancias distintas vistas por 10 hilos: " + vistas.size());
        System.out.println("Veces que se ejecutó el constructor: " + Configuracion.construcciones.get());
    }
}

class Configuracion {
    static final AtomicInteger construcciones = new AtomicInteger();
    private final Map<String, String> valores = new ConcurrentHashMap<>();

    // 1) Constructor privado: nadie puede hacer new Configuracion()
    private Configuracion() {
        construcciones.incrementAndGet();
    }

    // 2) Idiom "holder": la JVM garantiza carga perezosa y segura entre hilos
    private static class Holder {
        static final Configuracion INSTANCIA = new Configuracion();
    }

    // 3) Punto de acceso global
    public static Configuracion getInstancia() {
        return Holder.INSTANCIA;
    }

    public void set(String clave, String valor) { valores.put(clave, valor); }

    public String get(String clave) { return valores.get(clave); }
}
