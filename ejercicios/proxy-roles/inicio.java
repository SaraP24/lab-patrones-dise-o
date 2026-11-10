interface Documento {
    void mostrar();
}

// No modifiques Documento ni DocumentoReal.
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
        // TODO 1: guardá el nombre y el rol recibidos en sus atributos (usá this.).
        //         No crees un DocumentoReal aquí: documentoReal ya comienza en null.
    }

    @Override
    public void mostrar() {
        // TODO 2: comprobá si el rol es DOCENTE o ADMINISTRADOR.
        //         Usá equalsIgnoreCase() y uní las comparaciones con ||.
        //         Pista: "DOCENTE".equalsIgnoreCase(rol) también evita un error si rol es null.
        //
        // TODO 3: si tiene permiso:
        //         - si documentoReal es null, creá un DocumentoReal con el nombre guardado
        //         - llamá a documentoReal.mostrar() (tanto si lo acabás de crear como si ya existía)
        //
        // TODO 4: si no tiene permiso, mostrá: Acceso denegado para el rol: [rol]
    }
}
