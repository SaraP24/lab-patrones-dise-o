class Luces {
    public void encender() {
        System.out.println("Luces encendidas");
    }

    public void apagar() {
        System.out.println("Luces apagadas");
    }
}

class Proyector {
    public void encender() {
        System.out.println("Proyector encendido");
    }

    public void apagar() {
        System.out.println("Proyector apagado");
    }
}

class AireAcondicionado {
    public void encender() {
        System.out.println("Aire acondicionado encendido");
    }

    public void apagar() {
        System.out.println("Aire acondicionado apagado");
    }
}

class AulaFacade {
    private Luces luces;
    private Proyector proyector;
    private AireAcondicionado aire;

    public AulaFacade(Luces luces, Proyector proyector, AireAcondicionado aire) {
        this.luces = luces;
        this.proyector = proyector;
        this.aire = aire;
    }

    public void iniciarClase() {
        System.out.println("Preparando el aula...");
        luces.encender();
        proyector.encender();
        aire.encender();
    }

    public void finalizarClase() {
        System.out.println("Cerrando el aula...");
        luces.apagar();
        proyector.apagar();
        aire.apagar();
    }
}
