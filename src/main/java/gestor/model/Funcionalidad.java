package gestor.model;

import java.util.Date;

public class Funcionalidad extends Tarea {

    private String descripcion;
    private String criteriosAceptacion;

    public Funcionalidad(String titulo, String prioridad, Date fechaLimite, String descripcion) {
        super(titulo, prioridad, fechaLimite);
        this.descripcion = descripcion;
    }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getCriteriosAceptacion() { return criteriosAceptacion; }
    public void setCriteriosAceptacion(String criteriosAceptacion) { this.criteriosAceptacion = criteriosAceptacion; }

    @Override
    public String toString() {
        return "Funcionalidad{titulo='" + getTitulo() + "', descripcion='" + descripcion + "'}";
    }
}
