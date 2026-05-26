package gestor.model;

import gestor.interfaces.Actividad;
import gestor.interfaces.Estado;
import gestor.interfaces.Mediador;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Tarea implements Actividad {

    private String titulo;
    private Estado estado;
    private Date fechaLimite;
    private String prioridad;
    private Mediador mediador;
    private List<Actividad> subActividades = new ArrayList<>();

    public Tarea(String titulo, String prioridad, Date fechaLimite) {
        this.titulo = titulo;
        this.prioridad = prioridad;
        this.fechaLimite = fechaLimite;
    }

    // Memento
    public TareaMemento crearMemento() {
        return new TareaMemento(titulo, estado, fechaLimite, prioridad);
    }

    public void restaurar(TareaMemento memento) {
        this.titulo = memento.getTitulo();
        this.estado = memento.getEstado();
        this.fechaLimite = memento.getFechaLimite();
        this.prioridad = memento.getPrioridad();
        System.out.println("[Tarea] Estado restaurado: " + titulo);
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
        estado.crearTarea(this);
        if (mediador != null) mediador.notificar(this, "ESTADO_CAMBIADO");
    }

    @Override
    public void agregarActividad(Actividad a) { subActividades.add(a); }

    @Override
    public void eliminarActividad(Actividad a) { subActividades.remove(a); }

    @Override
    public void setMediador(Mediador mediador) { this.mediador = mediador; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public Estado getEstado() { return estado; }
    public Date getFechaLimite() { return fechaLimite; }
    public void setFechaLimite(Date fechaLimite) { this.fechaLimite = fechaLimite; }
    public String getPrioridad() { return prioridad; }
    public void setPrioridad(String prioridad) { this.prioridad = prioridad; }

    @Override
    public String toString() {
        return "Tarea{titulo='" + titulo + "', prioridad='" + prioridad + "'}";
    }
}
