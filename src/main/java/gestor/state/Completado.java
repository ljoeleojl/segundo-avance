package gestor.state;

import gestor.interfaces.Estado;
import gestor.model.Tarea;

public class Completado implements Estado {

    private Tarea contexto;

    public Completado(Tarea contexto) {
        this.contexto = contexto;
    }

    @Override
    public void crearTarea(Tarea contexto) {
        System.out.println("[Estado] La tarea '" + contexto.getTitulo() + "' está COMPLETADA. No se pueden crear sub-tareas.");
    }

    @Override
    public String toString() { return "Completado"; }
}
