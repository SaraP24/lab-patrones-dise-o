interface Silla {
    String describir();
}

interface Mesa {
    String describir();
}

class SillaModerna implements Silla {
    public String describir() { return "Silla moderna de metal"; }
}

class MesaModerna implements Mesa {
    public String describir() { return "Mesa moderna de vidrio"; }
}

class SillaVictoriana implements Silla {
    public String describir() { return "Silla victoriana de madera tallada"; }
}

class MesaVictoriana implements Mesa {
    public String describir() { return "Mesa victoriana de roble"; }
}

interface FabricaMuebles {
    Silla crearSilla();
    Mesa crearMesa();
}

class FabricaModerna implements FabricaMuebles {
    public Silla crearSilla() { return new SillaModerna(); }
    public Mesa crearMesa() { return new MesaModerna(); }
}

class FabricaVictoriana implements FabricaMuebles {
    public Silla crearSilla() { return new SillaVictoriana(); }
    public Mesa crearMesa() { return new MesaVictoriana(); }
}

class Comedor {
    private FabricaMuebles fabrica;

    public Comedor(FabricaMuebles fabrica) {
        this.fabrica = fabrica;
    }

    public void amueblar() {
        Mesa mesa = fabrica.crearMesa();
        Silla silla = fabrica.crearSilla();
        System.out.println("Mesa: " + mesa.describir());
        System.out.println("Silla: " + silla.describir());
    }
}
