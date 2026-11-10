// Esta clase ya está implementada y no debe modificarse.
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
        // TODO 1: comprobá el valor de tienePermiso.
        // TODO 2: si tiene permiso, pedile al documento real que muestre su contenido.
        // TODO 3: si no, mostrá exactamente:
        //         Acceso denegado. No tienes permiso para ver este documento.
    }
}
