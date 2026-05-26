package gestor.chain;

import gestor.interfaces.Mediador;
import gestor.interfaces.Usuario;

public class UsuarioAdmin implements Usuario {

    private Usuario siguiente;
    private Mediador mediador;

    @Override
    public void asignarReunion() {
        System.out.println("[UsuarioAdmin] Asignando reunión con permisos de ADMIN");
        if (mediador != null) mediador.notificar(this, "REUNION_ADMIN");
    }

    @Override
    public void asignarTarea() {
        System.out.println("[UsuarioAdmin] Asignando tarea con permisos de ADMIN");
        if (mediador != null) mediador.notificar(this, "TAREA_ADMIN");
    }

    @Override
    public void setMediador(Mediador mediador) { this.mediador = mediador; }

    @Override
    public void setSiguiente(Usuario siguiente) { this.siguiente = siguiente; }

    @Override
    public void manejar(Object solicitud) {
        // Admin puede manejar todo
        System.out.println("[UsuarioAdmin] Solicitud manejada por ADMIN: " + solicitud);
    }
}
