import java.util.ArrayList;
import java.util.List;

class Libro {
    private String isbn;
    private String titulo;
    private String autor;

    public Libro(String isbn, String titulo, String autor) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    @Override
    public String toString() {
        return "Libro [isbn=" + isbn + ", titulo=" + titulo + ", autor=" + autor + "]";
    }
}

interface LibroDAO {
    boolean agregar(Libro libro);
    Libro buscarPorIsbn(String isbn);
    List<Libro> listarTodos();
    boolean eliminar(String isbn);
}

class LibroDAOMemoria implements LibroDAO {
    private List<Libro> libros = new ArrayList<>();

    @Override
    public boolean agregar(Libro libro) {
        if (buscarPorIsbn(libro.getIsbn()) != null) {
            return false;
        }
        libros.add(libro);
        return true;
    }

    @Override
    public Libro buscarPorIsbn(String isbn) {
        for (Libro l : libros) {
            if (l.getIsbn().equals(isbn)) {
                return l;
            }
        }
        return null;
    }

    @Override
    public List<Libro> listarTodos() {
        return new ArrayList<>(libros);
    }

    @Override
    public boolean eliminar(String isbn) {
        Libro l = buscarPorIsbn(isbn);
        if (l == null) {
            return false;
        }
        libros.remove(l);
        return true;
    }
}
