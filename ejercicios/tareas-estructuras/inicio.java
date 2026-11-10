import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class GestionarTareas {
    private LinkedList<String> lista;
    private Stack<String> pila;
    private Queue<String> cola;

    public GestionarTareas() {
        // TODO 1: inicializá las tres estructuras vacías
        //         (la cola puede ser un new LinkedList<>()).
    }

    public void agregarTarea(String tarea) {
        // TODO 2: agregá la tarea a la lista (add), a la pila (push) y a la cola (offer).
    }

    public void eliminarTarea(String tarea) {
        // TODO 3: en la lista, eliminá la tarea cuyo nombre coincide con el parámetro.
        // TODO 4: si la pila no está vacía, eliminá la ÚLTIMA tarea agregada (LIFO).
        // TODO 5: si la cola no está vacía, eliminá la PRIMERA tarea agregada (FIFO).
    }

    public void imprimirEstado() {
        // TODO 6: mostrá el contenido de las tres estructuras con este formato:
        //         Lista enlazada: [...]
        //         Pilas: [...]
        //         Colas: [...]
    }
}
