package gestor.proxy;

import gestor.interfaces.Mediador;
import gestor.interfaces.Usuario;
import gestor.model.UsuarioReal;

public class UsuarioProxy implements Usuario {

    private final UsuarioReal usuarioReal;
    private final String rol;

    public UsuarioProxy(UsuarioReal usuarioReal, String rol) {
        this.usuarioReal = usuarioReal;
        this.rol = rol;
    }

    private boolean tienePermiso(String accion) {
        switch (accion) {
            case "REUNION": return rol.equals("ADMIN") || rol.equals("ESTUDIANTE");
            case "TAREA":   return rol.equals("ADMIN") || rol.equals("ESTUDIANTE");
            case "REVISAR": return rol.equals("ADMIN");
            default:        return false;
        }
    }

    @Override
    public void asignarReunion() {
        if (tienePermiso("REUNION")) {
            usuarioReal.asignarReunion();
        } else {
            System.out.println("[Proxy] Acceso denegado: " + rol + " no puede asignar reuniones");
        }
    }

    @Override
    public void asignarTarea() {
        if (tienePermiso("TAREA")) {
            usuarioReal.asignarTarea();
        } else {
            System.out.println("[Proxy] Acceso denegado: " + rol + " no puede asignar tareas");
        }
    }

    public void revisarTarea() {
        if (tienePermiso("REVISAR")) {
            System.out.println("[Proxy] " + usuarioReal.getNombre() + " revisando tarea (ADMIN)");
        } else {
            System.out.println("[Proxy] Acceso denegado: solo ADMIN puede revisar tareas");
        }
    }

    @Override
    public void setMediador(Mediador mediador) { usuarioReal.setMediador(mediador); }

    @Override
    public void setSiguiente(Usuario siguiente) { usuarioReal.setSiguiente(siguiente); }

    @Override
    public void manejar(Object solicitud) { usuarioReal.manejar(solicitud); }

    public String getRol() { return rol; }
}
