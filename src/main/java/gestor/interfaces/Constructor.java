package gestor.interfaces;

import gestor.model.Reunion;

public interface Constructor {
    void reset();
    void setTitulo(String titulo);
    void setFecha(String fecha);
    void setPlataforma(Plataforma plataforma);
    void agregarParticipante(Usuario participante);
    void setMediador(Mediador mediador);
    Reunion build();
}
