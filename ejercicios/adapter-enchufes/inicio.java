// Importante para CodeRunner: la interfaz EnchufeEuropeo no debe llevar public al inicio.
interface EnchufeEuropeo {
    void conectarEnchufeEuropeo();
}

// Clase existente: no debería hacer falta cambiar su forma de uso.
class EnchufeAmericano {
    public void conectarEnchufeAmericano() {
        // TODO 1: mostrá "Enchufe americano conectado."
    }
}

class Adaptador implements EnchufeEuropeo {
    private EnchufeAmericano enchufeAmericano;

    public Adaptador(EnchufeAmericano enchufeAmericano) {
        // TODO 2: guardá la referencia al enchufe americano.
    }

    @Override
    public void conectarEnchufeEuropeo() {
        // TODO 3: utilizá internamente el enchufe americano (conectarEnchufeAmericano()).
        // TODO 4: mostrá "Usando un adaptador europeo."
    }
}
