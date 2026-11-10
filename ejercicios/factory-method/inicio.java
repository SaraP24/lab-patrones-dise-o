import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Logistica> empresas = List.of(
                new LogisticaTerrestre(),
                new LogisticaMaritima()
                // TODO 4: añade aquí new LogisticaAerea()
        );
        for (Logistica l : empresas) {
            l.planificarEntrega();
        }
    }
}

interface Transporte { String entregar(); }

class Camion implements Transporte {
    public String entregar() { return "Camión entrega por carretera"; }
}

// TODO 1: crea la clase Barco -> entregar() devuelve "Barco entrega por mar en contenedor"
// TODO 2: crea la clase Avion -> entregar() devuelve "Avión entrega por aire"

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
    protected Transporte crearTransporte() {
        // TODO 1b: devuelve un Barco
        throw new UnsupportedOperationException("TODO: LogisticaMaritima.crearTransporte()");
    }
}

// TODO 3: crea LogisticaAerea, que extienda Logistica y cree un Avion
