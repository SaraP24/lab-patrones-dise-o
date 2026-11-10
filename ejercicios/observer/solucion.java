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

    void suscribir(Observador o) { observadores.add(o); }

    void desuscribir(Observador o) { observadores.remove(o); }

    private void notificar(double temperatura) {
        for (Observador o : observadores) o.actualizar(temperatura);
    }

    void setTemperatura(double temperatura) {
        notificar(temperatura);
    }
}

class Pantalla implements Observador {
    private final String nombre;

    Pantalla(String nombre) { this.nombre = nombre; }

    public void actualizar(double t) { System.out.println("Pantalla[" + nombre + "]: " + t + " °C"); }
}

class AlertaCalor implements Observador {
    private final double umbral;

    AlertaCalor(double umbral) { this.umbral = umbral; }

    public void actualizar(double t) {
        if (t > umbral) System.out.println("¡ALERTA! Temperatura alta: " + t + " °C");
    }
}
