interface Documento {
    void mostrar();
}

class DocumentoReal implements Documento {
    private String nombre;

    public DocumentoReal(String nombre) {
        this.nombre = nombre;
        cargarDocumento();
    }

    private void cargarDocumento() {
        System.out.println("Cargando documento: " + nombre);
    }

    @Override
    public void mostrar() {
        System.out.println("Mostrando contenido de: " + nombre);
    }
}

class ProxyDocumento implements Documento {
    private String nombre;
    private String rol;
    private DocumentoReal documentoReal;

    public ProxyDocumento(String nombre, String rol) {
        this.nombre = nombre;
        this.rol = rol;
    }

    @Override
    public void mostrar() {
        boolean tienePermiso = "DOCENTE".equalsIgnoreCase(rol) || "ADMINISTRADOR".equalsIgnoreCase(rol);
        if (tienePermiso) {
            if (documentoReal == null) {
                documentoReal = new DocumentoReal(nombre);   // proxy virtual: se crea solo al usarlo
            }
            documentoReal.mostrar();
        } else {
            System.out.println("Acceso denegado para el rol: " + rol);   // proxy de protección
        }
    }
}
