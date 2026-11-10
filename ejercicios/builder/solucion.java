import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Pizza p1 = new Pizza.Builder("grande")
                .masa("fina")
                .ingrediente("jamón")
                .ingrediente("champiñones")
                .extraQueso(true)
                .build();
        Pizza p2 = new Pizza.Builder("mediana").build();
        System.out.println(p1);
        System.out.println(p2);
        try {
            new Pizza.Builder("gigante").build();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

class Pizza {
    private final String tamano;
    private final String masa;
    private final List<String> ingredientes;
    private final boolean extraQueso;

    private Pizza(Builder b) {
        this.tamano = b.tamano;
        this.masa = b.masa;
        this.ingredientes = List.copyOf(b.ingredientes);
        this.extraQueso = b.extraQueso;
    }

    @Override
    public String toString() {
        return "Pizza[tamaño=" + tamano + ", masa=" + masa + ", ingredientes=" + ingredientes
                + ", extraQueso=" + extraQueso + "]";
    }

    static class Builder {
        private final String tamano;              // obligatorio
        private String masa = "normal";           // opcionales con valor por defecto
        private final List<String> ingredientes = new ArrayList<>();
        private boolean extraQueso = false;

        Builder(String tamano) { this.tamano = tamano; }

        Builder masa(String masa) { this.masa = masa; return this; }

        Builder ingrediente(String ingrediente) { ingredientes.add(ingrediente); return this; }

        Builder extraQueso(boolean extraQueso) { this.extraQueso = extraQueso; return this; }

        Pizza build() {
            if (!List.of("pequeña", "mediana", "grande").contains(tamano)) {
                throw new IllegalArgumentException("tamaño no válido: " + tamano);
            }
            return new Pizza(this);
        }
    }
}
