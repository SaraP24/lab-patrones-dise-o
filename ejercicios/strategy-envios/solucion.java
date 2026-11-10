interface EstrategiaEnvio {
    int calcularCosto(int pesoKg);
}

class EnvioEstandar implements EstrategiaEnvio {
    @Override
    public int calcularCosto(int pesoKg) {
        return 100 + 20 * pesoKg;
    }
}

class EnvioExpress implements EstrategiaEnvio {
    @Override
    public int calcularCosto(int pesoKg) {
        return 250 + 35 * pesoKg;
    }
}

class RetiroEnLocal implements EstrategiaEnvio {
    @Override
    public int calcularCosto(int pesoKg) {
        return 0;
    }
}

class Pedido {
    private int pesoKg;
    private EstrategiaEnvio estrategia;

    public Pedido(int pesoKg, EstrategiaEnvio estrategia) {
        this.pesoKg = pesoKg;
        this.estrategia = estrategia;
    }

    public void setEstrategia(EstrategiaEnvio estrategia) {
        this.estrategia = estrategia;
    }

    public int costoEnvio() {
        return estrategia.calcularCosto(pesoKg);
    }
}
