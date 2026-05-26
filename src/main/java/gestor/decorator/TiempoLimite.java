package gestor.decorator;

import gestor.interfaces.Actividad;
import java.util.Date;

public class TiempoLimite extends ProyectoDecorator {

    private Date fechaLimite;

    public TiempoLimite(Actividad wrappee, Date fechaLimite) {
        super(wrappee);
        this.fechaLimite = fechaLimite;
    }

    @Override
    public void agregarActividad(Actividad a) {
        System.out.println("[TiempoLimite] Actividad con fecha límite: " + fechaLimite);
        wrappee.agregarActividad(a);
    }

    @Override
    public void eliminarActividad(Actividad a) {
        System.out.println("[TiempoLimite] Eliminando actividad con restricción de tiempo");
        wrappee.eliminarActividad(a);
    }

    public Date getFechaLimite() { return fechaLimite; }
    public void setFechaLimite(Date fechaLimite) { this.fechaLimite = fechaLimite; }
}
