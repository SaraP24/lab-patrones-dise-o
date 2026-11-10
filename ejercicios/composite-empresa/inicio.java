import java.util.ArrayList;
import java.util.List;

// Componente: lo que tienen en común un empleado y un departamento
interface ComponenteEmpresa {
    int getSalario();
    void mostrar(String sangria);
}

// Hoja
class Empleado implements ComponenteEmpresa {
    private String nombre;
    private int salario;

    public Empleado(String nombre, int salario) {
        this.nombre = nombre;
        this.salario = salario;
    }

    @Override
    public int getSalario() {
        // TODO 1: devolvé el salario del empleado.
        return 0;
    }

    @Override
    public void mostrar(String sangria) {
        // TODO 2: mostrá: [sangria][nombre] ($[salario])
        //         Ejemplo con sangria "  ": "  Ana ($50000)"
    }
}

// Compuesto: contiene empleados y otros departamentos
class Departamento implements ComponenteEmpresa {
    private String nombre;
    private List<ComponenteEmpresa> integrantes = new ArrayList<>();

    public Departamento(String nombre) {
        this.nombre = nombre;
    }

    public void agregar(ComponenteEmpresa c) {
        // TODO 3: agregá el componente a la lista.
    }

    @Override
    public int getSalario() {
        // TODO 4: sumá getSalario() de todos los integrantes.
        //         No preguntes si son Empleado o Departamento: tratalos igual.
        return 0;
    }

    @Override
    public void mostrar(String sangria) {
        // TODO 5: mostrá: [sangria]Departamento [nombre] (total $[getSalario()])
        //         y después cada integrante con dos espacios más de sangría (sangria + "  ").
    }
}
