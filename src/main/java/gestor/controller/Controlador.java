package gestor.controller;

import gestor.adapter.TeamsAdapter;
import gestor.builder.ReunionConcreto;
import gestor.chain.UsuarioAdmin;
import gestor.chain.UsuarioBasico;
import gestor.chain.UsuarioEstudiante;
import gestor.command.CambiarEstadoCommand;
import gestor.decorator.Prioridad;
import gestor.decorator.TiempoLimite;
import gestor.factory.FabricaGestorConcreta;
import gestor.interfaces.Command;
import gestor.interfaces.FabricaGestor;
import gestor.model.Bug;
import gestor.model.Funcionalidad;
import gestor.model.HistorialCambios;
import gestor.model.Proyecto;
import gestor.model.Reunion;
import gestor.model.Tarea;
import gestor.model.UsuarioReal;
import gestor.proxy.UsuarioProxy;
import gestor.singleton.GestorPrincipalSingleton;
import gestor.state.Completado;
import gestor.state.EnRevision;
import gestor.state.Enviado;
import gestor.strategy.EstrategiaGestion;
import gestor.strategy.EstrategiaGestionAvanzada;
import gestor.strategy.EstrategiaGestionBasica;
import java.util.Date;

public class Controlador {

    private EstrategiaGestion estrategia;
    private final GestorPrincipalSingleton gestor;
    private final FabricaGestor fabrica;

    private int contadorTareas = 1;
    private int contadorBugs = 1;
    private int contadorFuncionalidades = 1;
    private int contadorProyectos = 1;
    private int contadorEstados = 0;

    public Controlador() {
        this.gestor = GestorPrincipalSingleton.getInstancia();
        this.fabrica = new FabricaGestorConcreta();
        this.fabrica.setMediador(gestor);
    }

    public void setEstrategia(EstrategiaGestion estrategia) {
        this.estrategia = estrategia;
    }

    public void gestionDeProyecto(GestorPrincipalSingleton gestor) {
        if (estrategia == null) {
            System.out.println("[Controlador] No hay estrategia definida");
            return;
        }
        estrategia.ejecutar(gestor);
        System.out.println("[Controlador] Estrategia ejecutada con el gestor principal");
    }

    public void ejecutar(GestorPrincipalSingleton gestor) {
        System.out.println("[Controlador] Ejecutando con gestor: " + gestor);
        gestionDeProyecto(gestor);
    }

    // ─────────────────────────────────────────────
    // Acciones con nombre del usuario
    // ─────────────────────────────────────────────

    public void crearTareaDesdeVista(String nombre) {
        String titulo = (nombre != null && !nombre.isBlank()) ? nombre : "Tarea " + contadorTareas;
        Tarea tarea = fabrica.crearTarea(titulo, "MEDIA", new Date());
        gestor.setTarea(tarea);
        contadorTareas++;
    }

    public void crearBugDesdeVista(String nombre) {
        String titulo = (nombre != null && !nombre.isBlank()) ? nombre : "Bug " + contadorBugs;
        Bug bug = fabrica.crearBug(titulo, "ALTA", new Date(), "Error reportado desde la interfaz", "CRITICO");
        gestor.setBug(bug);
        contadorBugs++;
    }

    public void crearFuncionalidadDesdeVista(String nombre) {
        String titulo = (nombre != null && !nombre.isBlank()) ? nombre : "Funcionalidad " + contadorFuncionalidades;
        Funcionalidad funcionalidad = fabrica.crearFuncionalidad(titulo, "MEDIA", new Date(), "Funcionalidad creada desde la interfaz");
        gestor.setFunc(funcionalidad);
        contadorFuncionalidades++;
    }

    public void crearProyectoDesdeVista(String nombre) {
        String titulo = (nombre != null && !nombre.isBlank()) ? nombre : "Proyecto " + contadorProyectos;
        Proyecto proyecto = fabrica.crearProyecto(titulo, "Proyecto creado desde la interfaz");
        gestor.setProyecto(proyecto);
        contadorProyectos++;
    }

    /**
     * Crea una reunión a partir de los datos introducidos por el usuario.
     * Usa el patrón Builder (ReunionConcreto) y notifica via Mediador.
     *
     * @param titulo  Nombre de la reunión
     * @param fecha   Fecha en formato texto (ej. "2026-06-10")
     * @param plataforma  Nombre de la plataforma ("Teams", "Zoom", etc.)
     * @return La Reunion construida
     */
    public Reunion crearReunionDesdeVista(String titulo, String fecha, String plataforma) {
        ReunionConcreto builder = new ReunionConcreto();
        builder.setMediador(gestor);

        String tituloFinal = (titulo != null && !titulo.isBlank()) ? titulo : "Reunión sin título";
        String fechaFinal  = (fecha  != null && !fecha.isBlank())  ? fecha  : "Sin fecha";

        builder.setTitulo(tituloFinal);
        builder.setFecha(fechaFinal);

        // Si la plataforma es Teams usamos el adapter; si no, se deja null (Reunion maneja null)
        if ("teams".equalsIgnoreCase(plataforma) || plataforma == null || plataforma.isBlank()) {
            builder.setPlataforma(new TeamsAdapter());
        }
        // Aquí se podrían agregar más plataformas con sus adapters

        Reunion reunion = builder.build();
        gestor.notificar(reunion, "REUNION_CREADA");
        return reunion;
    }

    // ─────────────────────────────────────────────
    // Acciones sin entrada (mantienen compatibilidad)
    // ─────────────────────────────────────────────

    public void crearTareaDesdeVista() {
        crearTareaDesdeVista(null);
    }

    public void crearBugDesdeVista() {
        crearBugDesdeVista(null);
    }

    public void crearFuncionalidadDesdeVista() {
        crearFuncionalidadDesdeVista(null);
    }

    public void crearProyectoDesdeVista() {
        crearProyectoDesdeVista(null);
    }

    public void cambiarEstadoTareaDesdeVista() {
        Tarea tarea = gestor.getTarea();
        if (tarea == null) {
            tarea = fabrica.crearTarea("Tarea automática para cambio de estado", "MEDIA", new Date());
            gestor.setTarea(tarea);
        }
        int estadoActual = contadorEstados % 3;
        if (estadoActual == 0) {
            tarea.setEstado(new Enviado(tarea));
        } else if (estadoActual == 1) {
            tarea.setEstado(new EnRevision(tarea));
        } else {
            tarea.setEstado(new Completado(tarea));
        }
        contadorEstados++;
    }

    public void ejecutarEstrategiaBasicaDesdeVista() {
        setEstrategia(new EstrategiaGestionBasica());
        ejecutar(gestor);
    }

    public void ejecutarEstrategiaAvanzadaDesdeVista() {
        setEstrategia(new EstrategiaGestionAvanzada());
        ejecutar(gestor);
    }

    // ─────────────────────────────────────────────
    // Demo completa de patrones
    // ─────────────────────────────────────────────

    public String ejecutarDemoCompletaPatrones() {
        StringBuilder reporte = new StringBuilder();
        reporte.append("Demo completa ejecutada correctamente.\n\n");

        // 1. Singleton
        GestorPrincipalSingleton gestor1 = GestorPrincipalSingleton.getInstancia();
        GestorPrincipalSingleton gestor2 = GestorPrincipalSingleton.getInstancia();
        reporte.append("[Creacional] Singleton: ");
        reporte.append(gestor1 == gestor2 ? "misma instancia confirmada.\n" : "instancias diferentes.\n");

        // 2. Factory
        Tarea tareaDemo = fabrica.crearTarea("Tarea demo patrones", "ALTA", new Date());
        Bug bugDemo = fabrica.crearBug("Bug demo patrones", "ALTA", new Date(), "Bug generado en la demo completa", "CRITICO");
        Funcionalidad funcionalidadDemo = fabrica.crearFuncionalidad("Funcionalidad demo patrones", "MEDIA", new Date(), "Funcionalidad generada en la demo completa");
        Proyecto proyectoDemo = fabrica.crearProyecto("Proyecto demo patrones", "Proyecto usado para demostrar patrones");
        UsuarioReal adminDemo = fabrica.crearUsuario("Carlos Demo", "ADMIN");

        gestor.setTarea(tareaDemo);
        gestor.setBug(bugDemo);
        gestor.setFunc(funcionalidadDemo);
        gestor.setProyecto(proyectoDemo);
        gestor.setUsuario(adminDemo);
        reporte.append("[Creacional] Factory: creó tarea, bug, funcionalidad, proyecto y usuario.\n");

        // 3. Builder
        Reunion reunion = crearReunionDesdeVista("Reunión demo Builder", "2026-06-02", "Teams");
        reporte.append("[Creacional] Builder: construyó una reunión paso a paso: ");
        reporte.append(reunion).append("\n");

        // 4. Composite
        proyectoDemo.agregarActividad(tareaDemo);
        proyectoDemo.agregarActividad(bugDemo);
        proyectoDemo.agregarActividad(funcionalidadDemo);
        reporte.append("[Estructural] Composite: proyecto compuesto por tarea, bug y funcionalidad.\n");

        // 5. Decorator
        TiempoLimite proyectoConTiempo = new TiempoLimite(proyectoDemo, new Date());
        Prioridad proyectoDecorado = new Prioridad(proyectoConTiempo, "ALTA");
        proyectoDecorado.agregarActividad(tareaDemo);
        reporte.append("[Estructural] Decorator: proyecto decorado con prioridad y tiempo límite.\n");

        // 6. Adapter
        TeamsAdapter teams = new TeamsAdapter();
        String enlace = teams.crearEnlace();
        teams.iniciarSesion();
        reporte.append("[Estructural] Adapter: TeamsAdapter generó enlace: ");
        reporte.append(enlace).append("\n");

        // 7. Proxy
        UsuarioProxy proxyAdmin = new UsuarioProxy(adminDemo, "ADMIN");
        UsuarioProxy proxyBasico = new UsuarioProxy(new UsuarioReal("Ana Demo", "BASICO"), "BASICO");
        proxyAdmin.revisarTarea();
        proxyBasico.revisarTarea();
        reporte.append("[Estructural] Proxy: validó acceso ADMIN y bloqueó acceso BÁSICO.\n");

        // 8. State
        tareaDemo.setEstado(new Enviado(tareaDemo));
        tareaDemo.setEstado(new EnRevision(tareaDemo));
        tareaDemo.setEstado(new Completado(tareaDemo));
        reporte.append("[Comportamiento] State: tarea pasó por Enviado, En revisión y Completado.\n");

        // 9. Command + Memento
        HistorialCambios historial = new HistorialCambios();
        historial.guardar(tareaDemo.crearMemento());
        Command comando = new CambiarEstadoCommand(tareaDemo, new Enviado(tareaDemo));
        comando.ejecutar();
        comando.deshacer();
        if (historial.hayCambios()) {
            tareaDemo.restaurar(historial.obtenerUltimo());
        }
        reporte.append("[Comportamiento] Command + Memento: cambió estado y restauró estado anterior.\n");

        // 10. Chain of Responsibility
        UsuarioBasico basico = new UsuarioBasico();
        UsuarioEstudiante estudiante = new UsuarioEstudiante();
        UsuarioAdmin admin = new UsuarioAdmin();
        basico.setSiguiente(estudiante);
        estudiante.setSiguiente(admin);
        basico.manejar("SOLICITUD_ADMIN");
        basico.manejar("SOLICITUD_ESTUDIANTE");
        reporte.append("[Comportamiento] Chain of Responsibility: solicitud pasó por la cadena de usuarios.\n");

        // 11. Observer + Mediator
        reporte.append("[Comportamiento] Observer: las vistas recibieron eventos del gestor.\n");
        reporte.append("[Comportamiento] Mediator: el GestorPrincipal coordinó las notificaciones.\n");

        // 12. Strategy
        setEstrategia(new EstrategiaGestionBasica());
        ejecutar(gestor);
        setEstrategia(new EstrategiaGestionAvanzada());
        ejecutar(gestor);
        reporte.append("[Comportamiento] Strategy: ejecutó análisis básico y avanzado.\n");

        reporte.append("\n[Bonificación] MVC: la VistaSwing usa el Controlador, ");
        reporte.append("el Controlador opera sobre el modelo y el Gestor notifica a las vistas.\n");

        return reporte.toString();
    }

    public GestorPrincipalSingleton getGestor() {
        return gestor;
    }
}