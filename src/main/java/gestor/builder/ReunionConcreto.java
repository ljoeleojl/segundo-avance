package gestor.builder;

import gestor.interfaces.Constructor;
import gestor.interfaces.Mediador;
import gestor.interfaces.Plataforma;
import gestor.interfaces.Usuario;
import gestor.model.Reunion;

public class ReunionConcreto implements Constructor {

    private Reunion reunion;

    public ReunionConcreto() {
        this.reunion = new Reunion();
    }

    @Override
    public void reset() {
        this.reunion = new Reunion();
    }

    @Override
    public void setTitulo(String titulo) {
        reunion.setTitulo(titulo);
    }

    @Override
    public void setFecha(String fecha) {
        reunion.setFecha(fecha);
    }

    @Override
    public void setPlataforma(Plataforma plataforma) {
        reunion.setPlataforma(plataforma);
    }

    @Override
    public void agregarParticipante(Usuario participante) {
        reunion.agregarParticipante(participante);
    }

    @Override
    public void setMediador(Mediador mediador) {
        // La reunión no implementa Actividad directamente, pero se registra el mediador si es necesario
        System.out.println("[ReunionConcreto] Mediador establecido");
    }

    @Override
    public Reunion build() {
        Reunion result = this.reunion;
        reset(); // Listo para reutilizar el builder
        System.out.println("[ReunionConcreto] Reunión construida: " + result.getTitulo());
        return result;
    }
}
