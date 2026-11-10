class Nodo {
    int valor;
    Nodo izquierdo;
    Nodo derecho;

    public Nodo(int valor) {
        this.valor = valor;
        this.izquierdo = null;
        this.derecho = null;
    }
}

class ArbolBinarioBusqueda {
    private Nodo raiz;

    public ArbolBinarioBusqueda() {
        raiz = null;
    }

    public void insertar(int valor) {
        raiz = insertarRecursivo(raiz, valor);
    }

    private Nodo insertarRecursivo(Nodo nodo, int valor) {
        if (nodo == null) {
            return new Nodo(valor);
        }
        if (valor < nodo.valor) {
            nodo.izquierdo = insertarRecursivo(nodo.izquierdo, valor);
        } else if (valor > nodo.valor) {
            nodo.derecho = insertarRecursivo(nodo.derecho, valor);
        }
        // los valores repetidos no se insertan
        return nodo;
    }

    public void inOrder() {
        inOrderRecursivo(raiz);
    }

    private void inOrderRecursivo(Nodo nodo) {
        if (nodo == null) return;
        inOrderRecursivo(nodo.izquierdo);
        System.out.print(nodo.valor + " ");
        inOrderRecursivo(nodo.derecho);
    }
}
