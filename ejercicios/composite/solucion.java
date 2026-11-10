import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Carpeta utils = new Carpeta("utils");
        utils.agregar(new Archivo("Lib.java", 1000));

        Carpeta src = new Carpeta("src");
        src.agregar(new Archivo("Main.java", 200));
        src.agregar(utils);

        Carpeta proyecto = new Carpeta("proyecto");
        proyecto.agregar(new Archivo("README.md", 5));
        proyecto.agregar(src);
        proyecto.agregar(new Archivo("logo.png", 45));

        proyecto.imprimir("");
        System.out.println("Total: " + proyecto.tamano() + " KB");
    }
}

// Componente: lo que tienen en común hojas y contenedores
interface ElementoSistema {
    int tamano();
    void imprimir(String sangria);
}

// Hoja
class Archivo implements ElementoSistema {
    private final String nombre;
    private final int kb;

    Archivo(String nombre, int kb) { this.nombre = nombre; this.kb = kb; }

    public int tamano() { return kb; }

    public void imprimir(String sangria) { System.out.println(sangria + nombre + " (" + kb + " KB)"); }
}

// Compuesto: contiene otros componentes (hojas u otros compuestos)
class Carpeta implements ElementoSistema {
    private final String nombre;
    private final List<ElementoSistema> hijos = new ArrayList<>();

    Carpeta(String nombre) { this.nombre = nombre; }

    void agregar(ElementoSistema e) { hijos.add(e); }

    public int tamano() {
        int total = 0;
        for (ElementoSistema h : hijos) total += h.tamano();   // recursión transparente
        return total;
    }

    public void imprimir(String sangria) {
        System.out.println(sangria + nombre + "/ (" + tamano() + " KB)");
        for (ElementoSistema h : hijos) h.imprimir(sangria + "  ");
    }
}
