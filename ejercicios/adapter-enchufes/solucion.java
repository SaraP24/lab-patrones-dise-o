interface EnchufeEuropeo {
    void conectarEnchufeEuropeo();
}

class EnchufeAmericano {
    public void conectarEnchufeAmericano() {
        System.out.println("Enchufe americano conectado.");
    }
}

class Adaptador implements EnchufeEuropeo {
    private EnchufeAmericano enchufeAmericano;

    public Adaptador(EnchufeAmericano enchufeAmericano) {
        this.enchufeAmericano = enchufeAmericano;
    }

    @Override
    public void conectarEnchufeEuropeo() {
        enchufeAmericano.conectarEnchufeAmericano();
        System.out.println("Usando un adaptador europeo.");
    }
}
