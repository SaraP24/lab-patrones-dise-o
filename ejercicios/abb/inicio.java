class Nodo {
    int valor;
    Nodo izquierdo;
    Nodo derecho;

    public Nodo(int valor) {
        // TODO 1: inicializá el valor y los dos hijos (en null).
    }
}

class ArbolBinarioBusqueda {
    // Importante para CodeRunner: raiz debe ser PRIVADO.
    private Nodo raiz;

    public ArbolBinarioBusqueda() {
        // TODO 2: inicializá el árbol vacío.
    }

    public void insertar(int valor) {
        raiz = insertarRecursivo(raiz, valor);
    }

    private Nodo insertarRecursivo(Nodo nodo, int valor) {
        // TODO 3: si nodo es null, creá y devolvé un nuevo Nodo.
        // TODO 4: si valor es menor, insertá en el hijo izquierdo;
        //         si es mayor, en el derecho. Los repetidos NO se insertan.
        return nodo;
    }

    public void inOrder() {
        // TODO 5: iniciá el recorrido desde la raíz.
    }

    private void inOrderRecursivo(Nodo nodo) {
        // TODO 6: Subárbol izquierdo -> Nodo actual -> Subárbol derecho
        //         Imprimí cada valor seguido de un espacio: System.out.print(nodo.valor + " ");
    }
}
