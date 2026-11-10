// ---------- Productos (ya implementados) ----------
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

// ---------- Fábrica abstracta ----------
interface FabricaMuebles {
    Silla crearSilla();
    Mesa crearMesa();
}

class FabricaModerna implements FabricaMuebles {
    public Silla crearSilla() {
        // TODO 1: devolvé un objeto nuevo de la familia moderna.
        return null;
    }

    public Mesa crearMesa() {
        // TODO 2: devolvé un objeto nuevo de la familia moderna.
        return null;
    }
}

class FabricaVictoriana implements FabricaMuebles {
    public Silla crearSilla() {
        // TODO 3: devolvé un objeto nuevo de la familia victoriana.
        return null;
    }

    public Mesa crearMesa() {
        // TODO 4: devolvé un objeto nuevo de la familia victoriana.
        return null;
    }
}

// ---------- Cliente: solo conoce las interfaces ----------
class Comedor {
    private FabricaMuebles fabrica;

    public Comedor(FabricaMuebles fabrica) {
        // TODO 5: guardá la fábrica recibida.
    }

    public void amueblar() {
        // TODO 6: pedile a la fábrica una mesa y una silla y mostrá, en este orden:
        //         Mesa: [descripción de la mesa]
        //         Silla: [descripción de la silla]
        //         No uses new SillaModerna() ni new MesaVictoriana() aquí: usá la fábrica.
    }
}
