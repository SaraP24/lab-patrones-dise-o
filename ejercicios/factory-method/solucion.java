import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Logistica> empresas = List.of(
                new LogisticaTerrestre(),
                new LogisticaMaritima(),
                new LogisticaAerea());
        for (Logistica l : empresas) {
            l.planificarEntrega();
        }
    }
}

interface Transporte { String entregar(); }

class Camion implements Transporte {
    public String entregar() { return "Camión entrega por carretera"; }
}
class Barco implements Transporte {
    public String entregar() { return "Barco entrega por mar en contenedor"; }
}
class Avion implements Transporte {
    public String entregar() { return "Avión entrega por aire"; }
}

abstract class Logistica {
    // El "factory method": cada subclase decide qué Transporte crear.
    protected abstract Transporte crearTransporte();

    // La lógica común no sabe qué transporte concreto usa.
    public void planificarEntrega() {
        Transporte t = crearTransporte();
        System.out.println("Entrega planificada: " + t.entregar());
    }
}

class LogisticaTerrestre extends Logistica {
    protected Transporte crearTransporte() { return new Camion(); }
}
class LogisticaMaritima extends Logistica {
    protected Transporte crearTransporte() { return new Barco(); }
}
class LogisticaAerea extends Logistica {
    protected Transporte crearTransporte() { return new Avion(); }
}
