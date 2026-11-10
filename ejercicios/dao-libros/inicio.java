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

// Contrato de acceso a datos: el resto de la aplicación solo conoce esta interfaz.
interface LibroDAO {
    boolean agregar(Libro libro);          // false si ya existe un libro con ese ISBN
    Libro buscarPorIsbn(String isbn);      // null si no existe
    List<Libro> listarTodos();
    boolean eliminar(String isbn);         // true si existía y se eliminó
}

// Implementación en memoria (en producción habría otra con JDBC).
class LibroDAOMemoria implements LibroDAO {
    private List<Libro> libros = new ArrayList<>();

    @Override
    public boolean agregar(Libro libro) {
        // TODO 1: si ya hay un libro con el mismo ISBN, no lo agregues y devolvé false.
        //         Si no, agregalo y devolvé true. Pista: reutilizá buscarPorIsbn().
        return false;
    }

    @Override
    public Libro buscarPorIsbn(String isbn) {
        // TODO 2: recorré la lista y devolvé el libro cuyo ISBN sea igual (usá equals).
        //         Si no lo encontrás, devolvé null.
        return null;
    }

    @Override
    public List<Libro> listarTodos() {
        // TODO 3: devolvé una COPIA de la lista (new ArrayList<>(...)),
        //         para que nadie pueda modificar los datos sin pasar por el DAO.
        return null;
    }

    @Override
    public boolean eliminar(String isbn) {
        // TODO 4: buscá el libro; si existe, quitalo de la lista y devolvé true. Si no, false.
        return false;
    }
}
