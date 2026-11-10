import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        TiendaFacade tienda = new TiendaFacade();

        System.out.println("== Compra 1 ==");
        System.out.println("Resultado: " + tienda.comprar("Teclado", 59.90, "Calle Mayor 1", "ana@mail.com"));

        System.out.println("== Compra 2 ==");
        System.out.println("Resultado: " + tienda.comprar("Monitor", 199.00, "Calle Mayor 1", "ana@mail.com"));
    }
}

// ---------- Subsistemas (complejos, con muchas piezas) ----------
class Inventario {
    private final Map<String, Integer> stock = new HashMap<>(Map.of("Teclado", 1, "Monitor", 0));

    boolean reservar(String producto) {
        int n = stock.getOrDefault(producto, 0);
        if (n == 0) {
            System.out.println("Inventario: sin stock de " + producto);
            return false;
        }
        stock.put(producto, n - 1);
        System.out.println("Inventario: reservado " + producto);
        return true;
    }
}

class Pagos {
    void cobrar(double importe) {
        System.out.println("Pago: cobrados " + String.format(Locale.ROOT, "%.2f", importe) + " EUR");
    }
}

class Envios {
    void crearEtiqueta(String direccion) { System.out.println("Envío: etiqueta para " + direccion); }
}

class Notificaciones {
    void email(String destino) { System.out.println("Email: confirmación a " + destino); }
}

// ---------- Fachada: una sola puerta de entrada ----------
class TiendaFacade {
    private final Inventario inventario = new Inventario();
    private final Pagos pagos = new Pagos();
    private final Envios envios = new Envios();
    private final Notificaciones notificaciones = new Notificaciones();

    String comprar(String producto, double precio, String direccion, String email) {
        if (!inventario.reservar(producto)) return "CANCELADO";
        pagos.cobrar(precio);
        envios.crearEtiqueta(direccion);
        notificaciones.email(email);
        return "OK";
    }
}
