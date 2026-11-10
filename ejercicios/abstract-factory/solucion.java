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

// ---- Familia clara ----
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
class BotonOscuro implements Boton {
    public String pintar() { return "Botón negro con texto blanco"; }
}
class CheckboxOscuro implements Checkbox {
    public String pintar() { return "Checkbox con borde azul"; }
}
class FabricaOscura implements FabricaUI {
    public String nombre() { return "Tema oscuro"; }
    public Boton crearBoton() { return new BotonOscuro(); }
    public Checkbox crearCheckbox() { return new CheckboxOscuro(); }
}
