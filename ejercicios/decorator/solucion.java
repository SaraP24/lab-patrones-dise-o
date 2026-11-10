import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        Cafe c1 = new CafeSimple();
        Cafe c2 = new ConLeche(new CafeSimple());
        Cafe c3 = new ConNata(new ConCaramelo(new ConLeche(new CafeSimple())));
        Cafe c4 = new ConLeche(new ConLeche(new CafeSimple()));

        for (Cafe c : new Cafe[]{c1, c2, c3, c4}) {
            System.out.println(c.descripcion() + " -> " + String.format(Locale.ROOT, "%.2f", c.costo()));
        }
    }
}

interface Cafe {
    String descripcion();
    double costo();
}

class CafeSimple implements Cafe {
    public String descripcion() { return "Café"; }
    public double costo() { return 1.50; }
}

// Decorador base: ES un Cafe y TIENE un Cafe dentro
abstract class DecoradorCafe implements Cafe {
    protected final Cafe envuelto;

    DecoradorCafe(Cafe envuelto) { this.envuelto = envuelto; }

    public String descripcion() { return envuelto.descripcion(); }
    public double costo() { return envuelto.costo(); }
}

class ConLeche extends DecoradorCafe {
    ConLeche(Cafe c) { super(c); }
    public String descripcion() { return envuelto.descripcion() + ", leche"; }
    public double costo() { return envuelto.costo() + 0.40; }
}

class ConCaramelo extends DecoradorCafe {
    ConCaramelo(Cafe c) { super(c); }
    public String descripcion() { return envuelto.descripcion() + ", caramelo"; }
    public double costo() { return envuelto.costo() + 0.60; }
}

class ConNata extends DecoradorCafe {
    ConNata(Cafe c) { super(c); }
    public String descripcion() { return envuelto.descripcion() + ", nata"; }
    public double costo() { return envuelto.costo() + 0.50; }
}
