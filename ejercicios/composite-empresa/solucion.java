import java.util.ArrayList;
import java.util.List;

interface ComponenteEmpresa {
    int getSalario();
    void mostrar(String sangria);
}

class Empleado implements ComponenteEmpresa {
    private String nombre;
    private int salario;

    public Empleado(String nombre, int salario) {
        this.nombre = nombre;
        this.salario = salario;
    }

    @Override
    public int getSalario() {
        return salario;
    }

    @Override
    public void mostrar(String sangria) {
        System.out.println(sangria + nombre + " ($" + salario + ")");
    }
}

class Departamento implements ComponenteEmpresa {
    private String nombre;
    private List<ComponenteEmpresa> integrantes = new ArrayList<>();

    public Departamento(String nombre) {
        this.nombre = nombre;
    }

    public void agregar(ComponenteEmpresa c) {
        integrantes.add(c);
    }

    @Override
    public int getSalario() {
        int total = 0;
        for (ComponenteEmpresa c : integrantes) {
            total += c.getSalario();    // recursión: puede ser un empleado o un departamento
        }
        return total;
    }

    @Override
    public void mostrar(String sangria) {
        System.out.println(sangria + "Departamento " + nombre + " (total $" + getSalario() + ")");
        for (ComponenteEmpresa c : integrantes) {
            c.mostrar(sangria + "  ");
        }
    }
}
