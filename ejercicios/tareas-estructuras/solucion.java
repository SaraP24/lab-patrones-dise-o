import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class GestionarTareas {
    private LinkedList<String> lista;
    private Stack<String> pila;
    private Queue<String> cola;

    public GestionarTareas() {
        lista = new LinkedList<>();
        pila = new Stack<>();
        cola = new LinkedList<>();
    }

    public void agregarTarea(String tarea) {
        lista.add(tarea);
        pila.push(tarea);
        cola.offer(tarea);
    }

    public void eliminarTarea(String tarea) {
        lista.remove(tarea);          // por nombre
        if (!pila.isEmpty()) {
            pila.pop();               // LIFO: la última que entró
        }
        if (!cola.isEmpty()) {
            cola.poll();              // FIFO: la primera que entró
        }
    }

    public void imprimirEstado() {
        System.out.println("Lista enlazada: " + lista);
        System.out.println("Pilas: " + pila);
        System.out.println("Colas: " + cola);
    }
}
