package gestor.state;

import gestor.interfaces.Estado;
import gestor.model.Tarea;

public class Enviado implements Estado {

    private Tarea contexto;

    public Enviado(Tarea contexto) {
        this.contexto = contexto;
    }

    @Override
    public void crearTarea(Tarea contexto) {
        System.out.println("[Estado] Tarea '" + contexto.getTitulo() + "' ENVIADA. Esperando revisión...");
    }

    @Override
    public String toString() { return "Enviado"; }
}
