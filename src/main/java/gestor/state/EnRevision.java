package gestor.state;

import gestor.interfaces.Estado;
import gestor.model.Tarea;

public class EnRevision implements Estado {

    private Tarea contexto;

    public EnRevision(Tarea contexto) {
        this.contexto = contexto;
    }

    @Override
    public void crearTarea(Tarea contexto) {
        System.out.println("[Estado] Tarea '" + contexto.getTitulo() + "' EN REVISIÓN. Creando sub-tarea de corrección...");
    }

    @Override
    public String toString() { return "EnRevision"; }
}
