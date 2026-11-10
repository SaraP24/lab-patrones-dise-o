import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EstacionMeteorologica estacion = new EstacionMeteorologica();
        Observador movil = new Pantalla("Móvil");
        Observador web = new Pantalla("Web");
        estacion.suscribir(movil);
        estacion.suscribir(web);
        estacion.suscribir(new AlertaCalor(30));

        estacion.setTemperatura(25.0);
        estacion.setTemperatura(32.5);

        System.out.println("-- Web se da de baja --");
        estacion.desuscribir(web);
        estacion.setTemperatura(28.0);
    }
}

interface Observador {
    void actualizar(double temperatura);
}

// Sujeto (Subject / Observable)
class EstacionMeteorologica {
    private final List<Observador> observadores = new ArrayList<>();

    void suscribir(Observador o) {
        // TODO 1: añade el observador a la lista
    }

    void desuscribir(Observador o) {
        // TODO 2: quita el observador de la lista
    }

    private void notificar(double temperatura) {
        // TODO 3: llama a actualizar(temperatura) en cada observador
    }

    void setTemperatura(double temperatura) {
        // TODO 4: cada cambio de temperatura debe notificar a los observadores
    }
}

class Pantalla implements Observador {
    private final String nombre;

    Pantalla(String nombre) { this.nombre = nombre; }

    public void actualizar(double t) { System.out.println("Pantalla[" + nombre + "]: " + t + " °C"); }
}

// TODO 5: crea AlertaCalor implements Observador.
//         Recibe un umbral en el constructor y, si t > umbral, imprime:
//         "¡ALERTA! Temperatura alta: " + t + " °C"
class AlertaCalor implements Observador {
    AlertaCalor(double umbral) { }

    public void actualizar(double t) { }
}
