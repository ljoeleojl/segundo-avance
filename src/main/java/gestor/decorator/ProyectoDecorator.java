package gestor.decorator;

import gestor.interfaces.Actividad;
import gestor.interfaces.Mediador;

public abstract class ProyectoDecorator implements Actividad {

    protected Actividad wrappee;

    public ProyectoDecorator(Actividad wrappee) {
        this.wrappee = wrappee;
    }

    @Override
    public void agregarActividad(Actividad a) {
        wrappee.agregarActividad(a);
    }

    @Override
    public void eliminarActividad(Actividad a) {
        wrappee.eliminarActividad(a);
    }

    @Override
    public void setMediador(Mediador mediador) {
        wrappee.setMediador(mediador);
    }
}
