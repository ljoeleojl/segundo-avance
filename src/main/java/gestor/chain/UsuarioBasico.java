package gestor.chain;

import gestor.interfaces.Mediador;
import gestor.interfaces.Usuario;

public class UsuarioBasico implements Usuario {

    private Usuario siguiente;
    private Mediador mediador;
    private static final String ROL = "BASICO";

    @Override
    public void asignarReunion() {
        System.out.println("[UsuarioBasico] Asignando reunión (permisos básicos)");
        if (mediador != null) mediador.notificar(this, "REUNION_BASICO");
    }

    @Override
    public void asignarTarea() {
        System.out.println("[UsuarioBasico] Asignando tarea (permisos básicos)");
    }

    @Override
    public void setMediador(Mediador mediador) { this.mediador = mediador; }

    @Override
    public void setSiguiente(Usuario siguiente) { this.siguiente = siguiente; }

    @Override
    public void manejar(Object solicitud) {
        System.out.println("[UsuarioBasico] Intentando manejar solicitud: " + solicitud);
        if (siguiente != null) {
            System.out.println("[UsuarioBasico] Pasando solicitud al siguiente en la cadena");
            siguiente.manejar(solicitud);
        } else {
            System.out.println("[UsuarioBasico] Nadie en la cadena pudo manejar la solicitud");
        }
    }
}
