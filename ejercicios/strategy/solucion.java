import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        Carrito carrito = new Carrito(120.0);

        carrito.setEstrategia(new SinDescuento());
        imprimir("Sin descuento", carrito);

        carrito.setEstrategia(new DescuentoPorcentaje(10));
        imprimir("10% de descuento", carrito);

        carrito.setEstrategia(new DescuentoFijo(15));
        imprimir("15 EUR de descuento", carrito);

        // Como EstrategiaDescuento es una interfaz funcional, una lambda también es una estrategia
        carrito.setEstrategia(subtotal -> subtotal * 0.5);
        imprimir("Black Friday (lambda)", carrito);

        carrito.setEstrategia(new DescuentoFijo(500));
        imprimir("Descuento mayor que el total", carrito);
    }

    static void imprimir(String etiqueta, Carrito c) {
        System.out.println(etiqueta + ": " + String.format(Locale.ROOT, "%.2f", c.total()));
    }
}

@FunctionalInterface
interface EstrategiaDescuento {
    double aplicar(double subtotal);
}

class SinDescuento implements EstrategiaDescuento {
    public double aplicar(double subtotal) { return subtotal; }
}

class DescuentoPorcentaje implements EstrategiaDescuento {
    private final double porcentaje;

    DescuentoPorcentaje(double porcentaje) { this.porcentaje = porcentaje; }

    public double aplicar(double subtotal) { return subtotal * (1 - porcentaje / 100); }
}

class DescuentoFijo implements EstrategiaDescuento {
    private final double cantidad;

    DescuentoFijo(double cantidad) { this.cantidad = cantidad; }

    public double aplicar(double subtotal) { return Math.max(0, subtotal - cantidad); }
}

// Contexto: delega el cálculo en la estrategia, sin if/else por tipo de descuento
class Carrito {
    private final double subtotal;
    private EstrategiaDescuento estrategia = new SinDescuento();

    Carrito(double subtotal) { this.subtotal = subtotal; }

    void setEstrategia(EstrategiaDescuento e) { this.estrategia = e; }

    double total() { return estrategia.aplicar(subtotal); }
}
