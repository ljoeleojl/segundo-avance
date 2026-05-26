package gestor.model;

import gestor.interfaces.Plataforma;
import gestor.interfaces.Usuario;
import java.util.ArrayList;
import java.util.List;

public class Reunion {

    private String titulo;
    private String fecha;
    private Plataforma plataforma;
    private List<Usuario> participantes = new ArrayList<>();
    private List<Usuario> asistentes = new ArrayList<>();
    private ReporteReunion reporte;

    public Reunion() {}

    public ReporteReunion generarReporte() {
        reporte = new ReporteReunion();
        asistentes.forEach(reporte::agregarAsistente);
        reporte.setResumen("Reunión: " + titulo + " | Fecha: " + fecha);
        return reporte;
    }

    public void registrarAsistente(Usuario usuario) {
        if (!asistentes.contains(usuario)) {
            asistentes.add(usuario);
            System.out.println("[Reunion] Asistente registrado en: " + titulo);
        }
    }

    public void iniciar() {
        System.out.println("[Reunion] Iniciando reunión: " + titulo);
        if (plataforma != null) plataforma.iniciarSesion();
    }

    public void finalizar() {
        System.out.println("[Reunion] Finalizando reunión: " + titulo);
        generarReporte();
    }

    public double calcularAsistencia() {
        if (participantes.isEmpty()) return 0;
        return (double) asistentes.size() / participantes.size() * 100;
    }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }
    public Plataforma getPlataforma() { return plataforma; }
    public void setPlataforma(Plataforma plataforma) { this.plataforma = plataforma; }
    public List<Usuario> getParticipantes() { return participantes; }
    public void agregarParticipante(Usuario u) { participantes.add(u); }

    @Override
    public String toString() {
        return "Reunion{titulo='" + titulo + "', fecha='" + fecha + "'}";
    }
}
