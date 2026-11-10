import java.util.ArrayList;
import java.util.List;

interface PrototipoPersonaje {
    Personaje clonar();
}

class Personaje implements PrototipoPersonaje {
    private String nombre;
    private int nivel;
    private List<String> habilidades;

    public Personaje(String nombre, int nivel) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.habilidades = new ArrayList<>();
    }

    private Personaje(Personaje original) {
        this.nombre = original.nombre;
        this.nivel = original.nivel;
        this.habilidades = new ArrayList<>(original.habilidades);   // lista propia
    }

    @Override
    public Personaje clonar() {
        return new Personaje(this);
    }

    public void agregarHabilidad(String habilidad) {
        habilidades.add(habilidad);
    }

    public List<String> getHabilidades() {
        return habilidades;
    }

    @Override
    public String toString() {
        return "Personaje [nombre=" + nombre + ", nivel=" + nivel + ", habilidades=" + habilidades + "]";
    }
}
