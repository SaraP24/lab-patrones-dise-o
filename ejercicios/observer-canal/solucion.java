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
        System.out.println(nombre + " recibió aviso de " + canal + ": " + video);
    }
}

class Canal {
    private String nombre;
    private List<Suscriptor> suscriptores = new ArrayList<>();

    public Canal(String nombre) {
        this.nombre = nombre;
    }

    public void suscribir(Suscriptor s) {
        suscriptores.add(s);
    }

    public void desuscribir(Suscriptor s) {
        suscriptores.remove(s);
    }

    public int cantidadSuscriptores() {
        return suscriptores.size();
    }

    public void subirVideo(String titulo) {
        System.out.println(nombre + " publicó: " + titulo);
        for (Suscriptor s : suscriptores) {
            s.notificar(nombre, titulo);
        }
    }
}
