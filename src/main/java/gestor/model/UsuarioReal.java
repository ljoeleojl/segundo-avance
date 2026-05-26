package gestor.model;

import gestor.interfaces.Mediador;
import gestor.interfaces.Usuario;

public class UsuarioReal implements Usuario {

    private String nombre;
    private String rol;
    private Mediador mediador;
    private Usuario siguiente;

    public UsuarioReal(String nombre, String rol) {
        this.nombre = nombre;
        this.rol = rol;
    }

    @Override
    public void asignarReunion() {
        System.out.println("[UsuarioReal] " + nombre + " asignado a reunión");
        if (mediador != null) mediador.notificar(this, "REUNION_ASIGNADA");
    }

    @Override
    public void asignarTarea() {
        System.out.println("[UsuarioReal] " + nombre + " asignado a tarea");
        if (mediador != null) mediador.notificar(this, "TAREA_ASIGNADA");
    }

    @Override
    public void setMediador(Mediador mediador) { this.mediador = mediador; }

    @Override
    public void setSiguiente(Usuario siguiente) { this.siguiente = siguiente; }

    @Override
    public void manejar(Object solicitud) {
        System.out.println("[UsuarioReal] " + nombre + " manejando solicitud: " + solicitud);
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    @Override
    public String toString() {
        return "UsuarioReal{nombre='" + nombre + "', rol='" + rol + "'}";
    }
}
