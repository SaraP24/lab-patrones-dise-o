import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        // El servicio depende de la INTERFAZ: mañana puedes pasarle un UsuarioDAOJdbc sin tocarlo
        UsuarioDAO dao = new UsuarioDAOEnMemoria();

        System.out.println("Guardado: " + dao.guardar(new Usuario(0, "Ana", "ana@mail.com")));
        System.out.println("Guardado: " + dao.guardar(new Usuario(0, "Luis", "luis@mail.com")));

        System.out.println("Buscar 1: " + dao.buscarPorId(1).map(Usuario::toString).orElse("no encontrado"));

        dao.actualizar(new Usuario(1, "Ana García", "ana@mail.com"));
        System.out.println("Actualizado: " + dao.buscarPorId(1).orElseThrow());

        System.out.println("Eliminado 2: " + dao.eliminar(2));
        System.out.println("Buscar 2: " + dao.buscarPorId(2).map(Usuario::toString).orElse("no encontrado"));
        System.out.println("Total usuarios: " + dao.buscarTodos().size());
    }
}

record Usuario(int id, String nombre, String email) {}

// Contrato de acceso a datos: el resto de la app solo conoce esto
interface UsuarioDAO {
    Usuario guardar(Usuario u);            // asigna id nuevo y devuelve el usuario guardado
    Optional<Usuario> buscarPorId(int id);
    List<Usuario> buscarTodos();
    boolean actualizar(Usuario u);
    boolean eliminar(int id);
}

// Implementación en memoria (ideal para tests). En producción habría otra con JDBC/JPA.
class UsuarioDAOEnMemoria implements UsuarioDAO {
    private final Map<Integer, Usuario> tabla = new LinkedHashMap<>();
    private int siguienteId = 1;

    public Usuario guardar(Usuario u) {
        Usuario conId = new Usuario(siguienteId++, u.nombre(), u.email());
        tabla.put(conId.id(), conId);
        return conId;
    }

    public Optional<Usuario> buscarPorId(int id) { return Optional.ofNullable(tabla.get(id)); }

    public List<Usuario> buscarTodos() { return new ArrayList<>(tabla.values()); }

    public boolean actualizar(Usuario u) {
        if (!tabla.containsKey(u.id())) return false;
        tabla.put(u.id(), u);
        return true;
    }

    public boolean eliminar(int id) { return tabla.remove(id) != null; }
}
