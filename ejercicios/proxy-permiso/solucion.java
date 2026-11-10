public class Documento {
    private String contenido;

    public Documento(String contenido) {
        this.contenido = contenido;
    }

    public void verContenido() {
        System.out.println("Contenido del documento: " + contenido);
    }
}

public class ProxyDocumento {
    private Documento documento;
    private boolean tienePermiso;

    public ProxyDocumento(Documento documento, boolean tienePermiso) {
        this.documento = documento;
        this.tienePermiso = tienePermiso;
    }

    public void verContenido() {
        if (tienePermiso) {
            documento.verContenido();
        } else {
            System.out.println("Acceso denegado. No tienes permiso para ver este documento.");
        }
    }
}
