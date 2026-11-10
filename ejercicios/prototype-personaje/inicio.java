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

    // Constructor de copia: "original" es el personaje que querés copiar
    // y "this" representa el nuevo personaje.
    private Personaje(Personaje original) {
        // TODO 1: copiá el nombre y el nivel de original a los atributos del nuevo personaje.
        // TODO 2: creá un NUEVO ArrayList con los elementos de la lista original.
        //         Asignar directamente original.habilidades haría que ambos compartan la misma lista.
    }

    @Override
    public Personaje clonar() {
        // TODO 3: reemplazá el return null por un nuevo Personaje creado con el
        //         constructor de copia, pasándole this como original.
        return null;
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
