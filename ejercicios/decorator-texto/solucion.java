interface Texto {
    String mostrar();
}

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

abstract class DecoradorTexto implements Texto {
    protected Texto texto;

    public DecoradorTexto(Texto texto) {
        this.texto = texto;
    }
}

class Negrita extends DecoradorTexto {
    public Negrita(Texto texto) {
        super(texto);
    }

    @Override
    public String mostrar() {
        return "<b>" + texto.mostrar() + "</b>";
    }
}

class Cursiva extends DecoradorTexto {
    public Cursiva(Texto texto) {
        super(texto);
    }

    @Override
    public String mostrar() {
        return "<i>" + texto.mostrar() + "</i>";
    }
}

class Mayusculas extends DecoradorTexto {
    public Mayusculas(Texto texto) {
        super(texto);
    }

    @Override
    public String mostrar() {
        return texto.mostrar().toUpperCase();
    }
}
