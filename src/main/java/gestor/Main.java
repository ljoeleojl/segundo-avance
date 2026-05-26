package gestor;

import gestor.adapter.TeamsAdapter;
import gestor.builder.ReunionConcreto;
import gestor.chain.UsuarioAdmin;
import gestor.chain.UsuarioBasico;
import gestor.chain.UsuarioEstudiante;
import gestor.command.CambiarEstadoCommand;
import gestor.controller.Controlador;
import gestor.decorator.Prioridad;
import gestor.decorator.TiempoLimite;
import gestor.interfaces.Command;
import gestor.model.*;
import gestor.proxy.UsuarioProxy;
import gestor.singleton.GestorPrincipalSingleton;
import gestor.state.Completado;
import gestor.state.EnRevision;
import gestor.state.Enviado;
import gestor.view.Vista;

import java.util.Date;

public class Main {

    public static void main(String[] args) {

        System.out.println("====== INICIANDO SISTEMA DE GESTIÓN DE PROYECTOS ======\n");

        // ─── 1. Singleton + Mediador ─────────────────────────────
        GestorPrincipalSingleton gestor = GestorPrincipalSingleton.getInstancia();
        System.out.println("\n--- [1] Singleton ---");
        System.out.println("Misma instancia: " + (gestor == GestorPrincipalSingleton.getInstancia()));

        // ─── 2. Observer (MVC) ───────────────────────────────────
        System.out.println("\n--- [2] Observer / MVC ---");
        Vista vistaPrincipal = new Vista("Principal");
        Vista vistaSecundaria = new Vista("Secundaria");
        gestor.subscribe(vistaPrincipal);
        gestor.subscribe(vistaSecundaria);

        // ─── 3. Tarea + Mediador notifica observers ───────────────
        System.out.println("\n--- [3] Creando Tarea (Mediador notifica Observers) ---");
        Tarea tarea = new Tarea("Implementar login", "ALTA", new Date());
        gestor.setTarea(tarea);

        Bug bug = new Bug("Fix NullPointer en login", "ALTA", new Date(), "NPE en AuthService", "CRITICO");
        gestor.setBug(bug);

        // ─── 4. State pattern ────────────────────────────────────
        System.out.println("\n--- [4] State Pattern ---");
        tarea.setEstado(new Enviado(tarea));
        tarea.setEstado(new EnRevision(tarea));
        tarea.setEstado(new Completado(tarea));

        // ─── 5. Command + Memento ────────────────────────────────
        System.out.println("\n--- [5] Command + Memento ---");
        Command cmd = new CambiarEstadoCommand(tarea, new Enviado(tarea));
        cmd.ejecutar();
        cmd.deshacer(); // Restaura el estado anterior vía Memento

        // ─── 6. Builder ──────────────────────────────────────────
        System.out.println("\n--- [6] Builder Pattern ---");
        ReunionConcreto builder = new ReunionConcreto();
        UsuarioReal usuarioReal = new UsuarioReal("Carlos", "ADMIN");
        builder.setTitulo("Sprint Planning");
        builder.setFecha("2024-06-01");
        builder.setPlataforma(new TeamsAdapter());
        builder.agregarParticipante(usuarioReal);
        Reunion reunion = builder.build();
        System.out.println("Reunión construida: " + reunion);

        // ─── 7. Adapter ──────────────────────────────────────────
        System.out.println("\n--- [7] Adapter Pattern ---");
        TeamsAdapter teams = new TeamsAdapter();
        String link = teams.crearEnlace();
        System.out.println("Enlace generado: " + link);
        teams.iniciarSesion();

        // ─── 8. Decorator ────────────────────────────────────────
        System.out.println("\n--- [8] Decorator Pattern ---");
        Proyecto proyecto = new Proyecto("App Móvil", "Proyecto principal");
        gestor.setProyecto(proyecto);

        TiempoLimite proyectoConTiempo = new TiempoLimite(proyecto, new Date());
        Prioridad proyectoDecored = new Prioridad(proyectoConTiempo, "ALTA");
        proyectoDecored.agregarActividad(tarea);

        // ─── 9. Proxy ────────────────────────────────────────────
        System.out.println("\n--- [9] Proxy Pattern ---");
        UsuarioProxy adminProxy = new UsuarioProxy(usuarioReal, "ADMIN");
        UsuarioProxy basicoProxy = new UsuarioProxy(new UsuarioReal("Ana", "BASICO"), "BASICO");

        adminProxy.revisarTarea();   // Permitido
        basicoProxy.revisarTarea();  // Denegado

        // ─── 10. Chain of Responsibility ─────────────────────────
        System.out.println("\n--- [10] Chain of Responsibility ---");
        UsuarioBasico basico = new UsuarioBasico();
        UsuarioEstudiante estudiante = new UsuarioEstudiante();
        UsuarioAdmin admin = new UsuarioAdmin();

        basico.setSiguiente(estudiante);
        estudiante.setSiguiente(admin);

        basico.manejar("SOLICITUD_ADMIN");   // Pasa hasta admin
        basico.manejar("SOLICITUD_ESTUDIANTE"); // La atiende estudiante

        // ─── 11. Controlador MVC ─────────────────────────────────
        System.out.println("\n--- [11] Controlador MVC + Strategy ---");
        Controlador controlador = new Controlador();
        // La estrategia es un FabricaGestor; aquí usamos el proyecto como ejemplo
        controlador.setEstrategia(mediador -> System.out.println("[Estrategia] Ejecutando gestión con mediador: " + mediador));
        controlador.ejecutar(gestor);

        System.out.println("\n====== SISTEMA FINALIZADO ======");
    }
}
