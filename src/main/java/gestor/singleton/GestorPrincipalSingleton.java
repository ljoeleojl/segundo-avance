package gestor.singleton;

import gestor.builder.ReunionConcreto;
import gestor.interfaces.Mediador;
import gestor.interfaces.Observer;
import gestor.model.*;
import java.util.ArrayList;
import java.util.List;

/**
 * GestorPrincipalSingleton actúa como:
 * - Singleton: una única instancia global
 * - Mediador: coordina la comunicación entre todos los componentes
 * - Subject (Observable): notifica a los observadores (Vistas) sobre eventos
 */
public class GestorPrincipalSingleton implements Mediador {

    // Singleton
    private static GestorPrincipalSingleton instancia;

    // Observer
    private final List<Observer> observers = new ArrayList<>();

    // Estado interno del sistema
    private Tarea tarea;
    private Bug bug;
    private Funcionalidad func;
    private Proyecto proyecto;
    private ReunionConcreto reunionBuilder;
    private UsuarioReal usuario;

    // Constructor privado - Singleton
    private GestorPrincipalSingleton() {
        System.out.println("[GestorPrincipal] Instancia creada");
    }

    // ─── Singleton ───────────────────────────────────────────────
    public static GestorPrincipalSingleton getInstancia() {
        if (instancia == null) {
            synchronized (GestorPrincipalSingleton.class) {
                if (instancia == null) {
                    instancia = new GestorPrincipalSingleton();
                }
            }
        }
        return instancia;
    }

    // ─── Observer (Subject) ──────────────────────────────────────
    public void subscribe(Observer o) {
        observers.add(o);
        System.out.println("[GestorPrincipal] Observer suscrito: " + o.getClass().getSimpleName());
    }

    public void unsubscribe(Observer o) {
        observers.remove(o);
        System.out.println("[GestorPrincipal] Observer desuscrito");
    }

    public void notifyObservers(String evento) {
        for (Observer o : observers) {
            o.update(evento, this);
        }
    }

    // ─── Mediador ────────────────────────────────────────────────
    @Override
    public void notificar(Object notificacion, String evento) {
        System.out.println("[GestorPrincipal] Mediando evento '" + evento + "' de: " +
                notificacion.getClass().getSimpleName());

        switch (evento) {
            case "TAREA_CREADA":
                this.tarea = (notificacion instanceof Tarea) ? (Tarea) notificacion : this.tarea;
                notifyObservers("TAREA_CREADA");
                break;
            case "ESTADO_CAMBIADO":
                notifyObservers("ESTADO_CAMBIADO");
                break;
            case "REUNION_CREADA":
                notifyObservers("REUNION_CREADA");
                break;
            case "USUARIO_REGISTRADO":
                this.usuario = (notificacion instanceof UsuarioReal) ? (UsuarioReal) notificacion : this.usuario;
                notifyObservers("USUARIO_REGISTRADO");
                break;
            case "TAREA_ASIGNADA":
            case "REUNION_ASIGNADA":
                notifyObservers(evento);
                break;
            default:
                System.out.println("[GestorPrincipal] Evento no registrado: " + evento);
                notifyObservers(evento);
        }
    }

    // ─── Getters / Setters ───────────────────────────────────────
    public Tarea getTarea() { return tarea; }
    public void setTarea(Tarea tarea) {
        this.tarea = tarea;
        tarea.setMediador(this);
        notificar(tarea, "TAREA_CREADA");
    }

    public Bug getBug() { return bug; }
    public void setBug(Bug bug) {
        this.bug = bug;
        bug.setMediador(this);
        notificar(bug, "TAREA_CREADA");
    }

    public Funcionalidad getFunc() { return func; }
    public void setFunc(Funcionalidad func) {
        this.func = func;
        func.setMediador(this);
        notificar(func, "TAREA_CREADA");
    }

    public Proyecto getProyecto() { return proyecto; }
    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
        proyecto.setMediador(this);
        notificar(proyecto, "PROYECTO_CREADO");
    }

    public ReunionConcreto getReunionBuilder() { return reunionBuilder; }
    public void setReunionBuilder(ReunionConcreto reunionBuilder) {
        this.reunionBuilder = reunionBuilder;
        reunionBuilder.setMediador(this);
    }

    public UsuarioReal getUsuario() { return usuario; }
    public void setUsuario(UsuarioReal usuario) {
        this.usuario = usuario;
        usuario.setMediador(this);
        notificar(usuario, "USUARIO_REGISTRADO");
    }

    @Override
    public String toString() {
        return "GestorPrincipalSingleton{observers=" + observers.size() + "}";
    }
}
