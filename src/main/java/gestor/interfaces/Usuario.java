package gestor.interfaces;

public interface Usuario {
    void asignarReunion();
    void asignarTarea();
    void setMediador(Mediador mediador);
    void setSiguiente(Usuario siguiente);
    void manejar(Object solicitud);
}
