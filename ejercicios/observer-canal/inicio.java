import java.util.ArrayList;
import java.util.List;

interface Suscriptor {
    void notificar(String canal, String video);
}

class Usuario implements Suscriptor {
    private String nombre;

    public Usuario(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void notificar(String canal, String video) {
        // TODO 1: mostrá con este formato exacto:
        //         [nombre] recibió aviso de [canal]: [video]
    }
}

// Sujeto observado
class Canal {
    private String nombre;
    private List<Suscriptor> suscriptores = new ArrayList<>();

    public Canal(String nombre) {
        this.nombre = nombre;
    }

    public void suscribir(Suscriptor s) {
        // TODO 2: agregá el suscriptor a la lista.
    }

    public void desuscribir(Suscriptor s) {
        // TODO 3: quitá el suscriptor de la lista.
    }

    public int cantidadSuscriptores() {
        // TODO 4: devolvé cuántos suscriptores hay.
        return -1;
    }

    public void subirVideo(String titulo) {
        // TODO 5: mostrá "[nombre del canal] publicó: [titulo]"
        //         y después avisá a TODOS los suscriptores llamando a notificar(nombre, titulo),
        //         en el orden en que se suscribieron.
    }
}
