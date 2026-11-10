import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Circulo original = new Circulo(10, "rojo");
        original.etiquetas.add("base");

        Circulo copia = (Circulo) original.clonar();
        System.out.println("Original: " + original);
        System.out.println("Copia: " + copia);
        System.out.println("¿Mismo objeto? " + (original == copia));

        copia.radio = 25;
        copia.color = "azul";
        copia.etiquetas.add("copia");
        System.out.println("Copia modificada: " + copia);
        System.out.println("Original intacto: " + original);

        RegistroFormas registro = new RegistroFormas();
        registro.registrar("rect-verde", new Rectangulo(4, 2, "verde"));
        Forma r1 = registro.crear("rect-verde");
        Forma r2 = registro.crear("rect-verde");
        System.out.println("Desde registro: " + r1);
        System.out.println("¿Dos clones distintos? " + (r1 != r2));
    }
}

abstract class Forma {
    String color;
    List<String> etiquetas = new ArrayList<>();

    Forma(String color) { this.color = color; }

    // Constructor de copia: copia PROFUNDA de la lista
    Forma(Forma otra) {
        this.color = otra.color;
        this.etiquetas = new ArrayList<>(otra.etiquetas);
    }

    abstract Forma clonar();
}

class Circulo extends Forma {
    int radio;

    Circulo(int radio, String color) { super(color); this.radio = radio; }

    Circulo(Circulo otro) { super(otro); this.radio = otro.radio; }

    Forma clonar() { return new Circulo(this); }

    public String toString() { return "Círculo r=" + radio + " color=" + color + " etiquetas=" + etiquetas; }
}

class Rectangulo extends Forma {
    int ancho, alto;

    Rectangulo(int ancho, int alto, String color) { super(color); this.ancho = ancho; this.alto = alto; }

    Rectangulo(Rectangulo otro) { super(otro); this.ancho = otro.ancho; this.alto = otro.alto; }

    Forma clonar() { return new Rectangulo(this); }

    public String toString() { return "Rectángulo " + ancho + "x" + alto + " color=" + color; }
}

class RegistroFormas {
    private final Map<String, Forma> prototipos = new HashMap<>();

    void registrar(String clave, Forma f) { prototipos.put(clave, f); }

    Forma crear(String clave) { return prototipos.get(clave).clonar(); }
}
