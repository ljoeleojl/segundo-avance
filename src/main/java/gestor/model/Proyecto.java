package gestor.model;

import gestor.interfaces.Actividad;
import gestor.interfaces.Mediador;
import java.util.ArrayList;
import java.util.List;

public class Proyecto implements Actividad {

    private String nombre;
    private String descripcion;
    private List<Actividad> actividades = new ArrayList<>();
    private Mediador mediador;

    public Proyecto(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    @Override
    public void agregarActividad(Actividad a) {
        actividades.add(a);
        System.out.println("[Proyecto] Actividad agregada a: " + nombre);
    }

    @Override
    public void eliminarActividad(Actividad a) {
        actividades.remove(a);
        System.out.println("[Proyecto] Actividad eliminada de: " + nombre);
    }

    @Override
    public void setMediador(Mediador mediador) {
        this.mediador = mediador;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public List<Actividad> getActividades() { return actividades; }

    @Override
    public String toString() {
        return "Proyecto{nombre='" + nombre + "', actividades=" + actividades.size() + "}";
    }
}
