package gestor.chain;

import gestor.interfaces.Mediador;
import gestor.interfaces.Usuario;

public class UsuarioEstudiante implements Usuario {

    private Usuario siguiente;
    private Mediador mediador;

    @Override
    public void asignarReunion() {
        System.out.println("[UsuarioEstudiante] Asignando reunión como estudiante");
        if (mediador != null) mediador.notificar(this, "REUNION_ESTUDIANTE");
    }

    @Override
    public void asignarTarea() {
        System.out.println("[UsuarioEstudiante] Asignando tarea como estudiante");
        if (mediador != null) mediador.notificar(this, "TAREA_ESTUDIANTE");
    }

    @Override
    public void setMediador(Mediador mediador) { this.mediador = mediador; }

    @Override
    public void setSiguiente(Usuario siguiente) { this.siguiente = siguiente; }

    @Override
    public void manejar(Object solicitud) {
        if (solicitud.toString().contains("ESTUDIANTE")) {
            System.out.println("[UsuarioEstudiante] Solicitud manejada: " + solicitud);
        } else if (siguiente != null) {
            siguiente.manejar(solicitud);
        } else {
            System.out.println("[UsuarioEstudiante] No se pudo manejar la solicitud");
        }
    }
}
