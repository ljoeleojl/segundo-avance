package gestor.model;

import java.util.Date;

public class Bug extends Tarea {

    private String descripcionError;
    private String severidad;

    public Bug(String titulo, String prioridad, Date fechaLimite, String descripcionError, String severidad) {
        super(titulo, prioridad, fechaLimite);
        this.descripcionError = descripcionError;
        this.severidad = severidad;
    }

    public String getDescripcionError() { return descripcionError; }
    public void setDescripcionError(String descripcionError) { this.descripcionError = descripcionError; }
    public String getSeveridad() { return severidad; }
    public void setSeveridad(String severidad) { this.severidad = severidad; }

    @Override
    public String toString() {
        return "Bug{titulo='" + getTitulo() + "', severidad='" + severidad + "'}";
    }
}
