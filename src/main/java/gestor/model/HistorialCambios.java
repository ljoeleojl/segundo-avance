package gestor.model;

import java.util.Stack;

public class HistorialCambios {
    private final Stack<TareaMemento> mementos = new Stack<>();

    public void guardar(TareaMemento memento) {
        mementos.push(memento);
    }

    public TareaMemento obtenerUltimo() {
        if (!mementos.isEmpty()) {
            return mementos.pop();
        }
        return null;
    }

    public boolean hayCambios() {
        return !mementos.isEmpty();
    }
}
