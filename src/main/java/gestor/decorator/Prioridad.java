package gestor.decorator;

import gestor.interfaces.Actividad;

public class Prioridad extends ProyectoDecorator {

    private String nivel; // ALTA, MEDIA, BAJA

    public Prioridad(Actividad wrappee, String nivel) {
        super(wrappee);
        this.nivel = nivel;
    }

    @Override
    public void agregarActividad(Actividad a) {
        System.out.println("[Prioridad] Actividad con prioridad " + nivel + " agregada");
        wrappee.agregarActividad(a);
    }

    @Override
    public void eliminarActividad(Actividad a) {
        System.out.println("[Prioridad] Eliminando actividad de prioridad " + nivel);
        wrappee.eliminarActividad(a);
    }

    public String getNivel() { return nivel; }
    public void setNivel(String nivel) { this.nivel = nivel; }
}
