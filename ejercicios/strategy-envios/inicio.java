// La estrategia: cada forma de envío calcula su costo a partir del peso (en kg).
interface EstrategiaEnvio {
    int calcularCosto(int pesoKg);
}

class EnvioEstandar implements EstrategiaEnvio {
    @Override
    public int calcularCosto(int pesoKg) {
        // TODO 1: el envío estándar cuesta 100 fijos + 20 por kg.
        return 0;
    }
}

class EnvioExpress implements EstrategiaEnvio {
    @Override
    public int calcularCosto(int pesoKg) {
        // TODO 2: el envío express cuesta 250 fijos + 35 por kg.
        return 0;
    }
}

class RetiroEnLocal implements EstrategiaEnvio {
    @Override
    public int calcularCosto(int pesoKg) {
        // TODO 3: retirar en el local no tiene costo.
        return -1;
    }
}

// El contexto: no sabe cómo se calcula el envío, delega en la estrategia.
class Pedido {
    private int pesoKg;
    private EstrategiaEnvio estrategia;

    public Pedido(int pesoKg, EstrategiaEnvio estrategia) {
        this.pesoKg = pesoKg;
        this.estrategia = estrategia;
    }

    public void setEstrategia(EstrategiaEnvio estrategia) {
        // TODO 4: permití cambiar la estrategia en tiempo de ejecución.
    }

    public int costoEnvio() {
        // TODO 5: delegá el cálculo en la estrategia actual. No uses if ni instanceof.
        return 0;
    }
}
