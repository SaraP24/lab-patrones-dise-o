import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Imagen> galeria = List.of(
                new ProxyImagen("playa.jpg"),
                new ProxyImagen("montaña.jpg"),
                new ProxyImagen("ciudad.jpg"));
        System.out.println("Galería con " + galeria.size() + " imágenes (cargas reales: " + ImagenReal.cargas + ")");

        galeria.get(0).mostrar();
        galeria.get(0).mostrar();   // ya está cargada: no vuelve a leer del disco
        galeria.get(1).mostrar();

        System.out.println("Cargas reales: " + ImagenReal.cargas);
    }
}

interface Imagen {
    void mostrar();
}

// Objeto real: caro de crear (simula leer un fichero grande)
class ImagenReal implements Imagen {
    static int cargas = 0;
    private final String fichero;

    ImagenReal(String fichero) {
        this.fichero = fichero;
        cargas++;
        System.out.println("Cargando " + fichero + " desde disco...");
    }

    public void mostrar() { System.out.println("Mostrando " + fichero); }
}

// Proxy virtual: misma interfaz, crea el objeto real solo cuando hace falta
class ProxyImagen implements Imagen {
    private final String fichero;
    private ImagenReal real;

    ProxyImagen(String fichero) { this.fichero = fichero; }

    public void mostrar() {
        if (real == null) real = new ImagenReal(fichero);
        real.mostrar();
    }
}
