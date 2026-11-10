// Componente
interface Texto {
    String mostrar();
}

// Componente concreto
class TextoSimple implements Texto {
    private String contenido;

    public TextoSimple(String contenido) {
        this.contenido = contenido;
    }

    @Override
    public String mostrar() {
        return contenido;
    }
}

// Decorador base: ES un Texto y TIENE un Texto adentro.
abstract class DecoradorTexto implements Texto {
    protected Texto texto;

    public DecoradorTexto(Texto texto) {
        // TODO 1: guardá el texto que se va a decorar.
    }
}

// Decoradores concretos: cada uno agrega su comportamiento sobre texto.mostrar()
class Negrita extends DecoradorTexto {
    public Negrita(Texto texto) {
        super(texto);
    }

    @Override
    public String mostrar() {
        // TODO 2: devolvé el texto envuelto entre <b> y </b>
        return "";
    }
}

class Cursiva extends DecoradorTexto {
    public Cursiva(Texto texto) {
        super(texto);
    }

    @Override
    public String mostrar() {
        // TODO 3: devolvé el texto envuelto entre <i> y </i>
        return "";
    }
}

class Mayusculas extends DecoradorTexto {
    public Mayusculas(Texto texto) {
        super(texto);
    }

    @Override
    public String mostrar() {
        // TODO 4: devolvé el texto en mayúsculas (toUpperCase()).
        return "";
    }
}
