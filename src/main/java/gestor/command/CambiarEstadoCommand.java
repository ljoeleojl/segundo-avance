package gestor.command;

import gestor.interfaces.Command;
import gestor.interfaces.Estado;
import gestor.model.Tarea;
import gestor.model.TareaMemento;

public class CambiarEstadoCommand implements Command {

    private final Tarea tarea;
    private final Estado nuevoEstado;
    private TareaMemento memento;

    public CambiarEstadoCommand(Tarea tarea, Estado nuevoEstado) {
        this.tarea = tarea;
        this.nuevoEstado = nuevoEstado;
    }

    @Override
    public void ejecutar() {
        // Guardar estado actual antes de cambiar (Memento)
        this.memento = tarea.crearMemento();
        tarea.setEstado(nuevoEstado);
        System.out.println("[Command] Estado cambiado a: " + nuevoEstado);
    }

    @Override
    public void deshacer() {
        if (memento != null) {
            tarea.restaurar(memento);
            System.out.println("[Command] Deshacer: Estado restaurado");
        }
    }
}
