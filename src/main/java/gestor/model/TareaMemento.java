package gestor.model;

import gestor.interfaces.Estado;
import java.util.Date;

public class TareaMemento {
    private final String titulo;
    private final Estado estado;
    private final Date fechaLimite;
    private final String prioridad;

    public TareaMemento(String titulo, Estado estado, Date fechaLimite, String prioridad) {
        this.titulo = titulo;
        this.estado = estado;
        this.fechaLimite = fechaLimite != null ? new Date(fechaLimite.getTime()) : null;
        this.prioridad = prioridad;
    }

    public String getTitulo() { return titulo; }
    public Estado getEstado() { return estado; }
    public Date getFechaLimite() { return fechaLimite != null ? new Date(fechaLimite.getTime()) : null; }
    public String getPrioridad() { return prioridad; }
}
