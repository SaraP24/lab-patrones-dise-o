public class Main {
    public static void main(String[] args) {
        new Aplicacion(new FabricaClara()).pintar();
        new Aplicacion(new FabricaOscura()).pintar();
    }
}

// El cliente solo conoce las interfaces: nunca hace "new BotonClaro()".
class Aplicacion {
    private final FabricaUI fabrica;

    Aplicacion(FabricaUI fabrica) { this.fabrica = fabrica; }

    void pintar() {
        System.out.println("[" + fabrica.nombre() + "]");
        System.out.println(fabrica.crearBoton().pintar());
        System.out.println(fabrica.crearCheckbox().pintar());
    }
}

interface Boton { String pintar(); }
interface Checkbox { String pintar(); }

interface FabricaUI {
    String nombre();
    Boton crearBoton();
    Checkbox crearCheckbox();
}

// ---- Familia clara (ya hecha, úsala como modelo) ----
class BotonClaro implements Boton {
    public String pintar() { return "Botón blanco con texto negro"; }
}
class CheckboxClaro implements Checkbox {
    public String pintar() { return "Checkbox con borde gris"; }
}
class FabricaClara implements FabricaUI {
    public String nombre() { return "Tema claro"; }
    public Boton crearBoton() { return new BotonClaro(); }
    public Checkbox crearCheckbox() { return new CheckboxClaro(); }
}

// ---- Familia oscura ----
// TODO 1: crea BotonOscuro    -> pintar() devuelve "Botón negro con texto blanco"
// TODO 2: crea CheckboxOscuro -> pintar() devuelve "Checkbox con borde azul"

class FabricaOscura implements FabricaUI {
    public String nombre() { return "Tema oscuro"; }

    public Boton crearBoton() {
        // TODO 3: devuelve un BotonOscuro
        throw new UnsupportedOperationException("TODO: crearBoton() de FabricaOscura");
    }

    public Checkbox crearCheckbox() {
        // TODO 4: devuelve un CheckboxOscuro
        throw new UnsupportedOperationException("TODO: crearCheckbox() de FabricaOscura");
    }
}
