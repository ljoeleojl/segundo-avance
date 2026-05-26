package gestor.view;

import gestor.interfaces.Observer;

public class Vista implements Observer {

    private String nombre;

    public Vista(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void update(String evento, Object data) {
        System.out.println("[Vista:" + nombre + "] Evento recibido: " + evento + " | Data: " + data);
        renderizar(evento, data);
    }

    private void renderizar(String evento, Object data) {
        switch (evento) {
            case "TAREA_CREADA":
                System.out.println("[Vista:" + nombre + "] Renderizando nueva tarea: " + data);
                break;
            case "ESTADO_CAMBIADO":
                System.out.println("[Vista:" + nombre + "] Actualizando estado de tarea: " + data);
                break;
            case "REUNION_CREADA":
                System.out.println("[Vista:" + nombre + "] Mostrando nueva reunión: " + data);
                break;
            case "USUARIO_REGISTRADO":
                System.out.println("[Vista:" + nombre + "] Mostrando nuevo usuario: " + data);
                break;
            default:
                System.out.println("[Vista:" + nombre + "] Evento no reconocido: " + evento);
        }
    }

    public String getNombre() { return nombre; }
}
