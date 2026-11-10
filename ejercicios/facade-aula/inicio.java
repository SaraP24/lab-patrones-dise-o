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
        // TODO 1: guardá los tres dispositivos recibidos en sus atributos.
        //         Usá this. para distinguir atributos de parámetros. No crees dispositivos nuevos.
    }

    public void iniciarClase() {
        // TODO 2: mostrá "Preparando el aula..." y llamá a encender() de cada dispositivo
        //         en este orden: luces, proyector y aire acondicionado.
    }

    public void finalizarClase() {
        // TODO 3: mostrá "Cerrando el aula..." y llamá a apagar() de cada dispositivo,
        //         en el mismo orden.
    }
}
