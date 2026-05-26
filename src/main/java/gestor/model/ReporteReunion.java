package gestor.model;

import java.util.ArrayList;
import java.util.List;

public class ReporteReunion {

    private List<Object> asistentesFinales = new ArrayList<>();
    private String resumen;
    private String conclusiones;

    public double calcularAsistencia() {
        return asistentesFinales.size();
    }

    public String generarResumen() {
        return resumen != null ? resumen : "Sin resumen disponible";
    }

    public void agregarConclusion(String conclusion) {
        this.conclusiones = (conclusiones == null ? "" : conclusiones + " | ") + conclusion;
    }

    public String exportar() {
        return "=== REPORTE DE REUNIÓN ===\n" +
               "Resumen: " + generarResumen() + "\n" +
               "Conclusiones: " + conclusiones + "\n" +
               "Asistentes: " + asistentesFinales.size();
    }

    public void agregarAsistente(Object asistente) { asistentesFinales.add(asistente); }
    public List<Object> getAsistentesFinales() { return asistentesFinales; }
    public String getResumen() { return resumen; }
    public void setResumen(String resumen) { this.resumen = resumen; }
    public String getConclusiones() { return conclusiones; }
}
