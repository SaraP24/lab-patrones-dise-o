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

        // TODO 4: usa una lambda como estrategia: el "Black Friday" deja el precio a la mitad
        // carrito.setEstrategia(subtotal -> ...);
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

    public double aplicar(double subtotal) {
        // TODO 1: resta el porcentaje al subtotal (10% de 120 -> 108)
        return subtotal;
    }
}

class DescuentoFijo implements EstrategiaDescuento {
    private final double cantidad;

    DescuentoFijo(double cantidad) { this.cantidad = cantidad; }

    public double aplicar(double subtotal) {
        // TODO 2: resta la cantidad fija, pero el total nunca puede ser negativo (usa Math.max)
        return subtotal;
    }
}

// Contexto: delega el cálculo en la estrategia, sin if/else por tipo de descuento
class Carrito {
    private final double subtotal;
    private EstrategiaDescuento estrategia = new SinDescuento();

    Carrito(double subtotal) { this.subtotal = subtotal; }

    void setEstrategia(EstrategiaDescuento e) { this.estrategia = e; }

    double total() {
        // TODO 3: delega en la estrategia actual
        return subtotal;
    }
}
