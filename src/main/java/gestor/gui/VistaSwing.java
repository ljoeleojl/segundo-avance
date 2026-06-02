package gestor.gui;

import gestor.controller.Controlador;
import gestor.interfaces.Observer;
import gestor.model.Reunion;
import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class VistaSwing extends JFrame implements Observer {

    private final Controlador controlador;
    private JTextArea areaEventos;
    private JLabel etiquetaEstado;

    // ── Campo de entrada compartido ──
    private JTextField campoNombre;

    // ── Campos para reunión ──
    private JTextField campoTituloReunion;
    private JTextField campoFechaReunion;
    private JTextField campoPlataformaReunion;

    public VistaSwing() {
        this.controlador = new Controlador();
        setTitle("Gestor de Proyectos");
        setSize(900, 620);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        construirInterfaz();
    }

    private void construirInterfaz() {
        JPanel raiz = new JPanel(new BorderLayout(6, 6));
        raiz.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        // ── Panel izquierdo: acciones ──
        JPanel panelAcciones = new JPanel();
        panelAcciones.setLayout(new BoxLayout(panelAcciones, BoxLayout.Y_AXIS));
        panelAcciones.setBorder(BorderFactory.createTitledBorder("Acciones"));
        panelAcciones.setPreferredSize(new Dimension(260, 0));

        // Campo de nombre reutilizable
        panelAcciones.add(new JLabel("Nombre (proyecto / actividad):"));
        campoNombre = new JTextField();
        campoNombre.setMaximumSize(new Dimension(Integer.MAX_VALUE, 26));
        panelAcciones.add(campoNombre);
        panelAcciones.add(Box.createVerticalStrut(6));

        // Botones de actividades
        panelAcciones.add(boton("Nueva tarea",         e -> accionConNombre("tarea")));
        panelAcciones.add(Box.createVerticalStrut(4));
        panelAcciones.add(boton("Nuevo bug",           e -> accionConNombre("bug")));
        panelAcciones.add(Box.createVerticalStrut(4));
        panelAcciones.add(boton("Nueva funcionalidad", e -> accionConNombre("funcionalidad")));
        panelAcciones.add(Box.createVerticalStrut(4));
        panelAcciones.add(boton("Nuevo proyecto",      e -> accionConNombre("proyecto")));
        panelAcciones.add(Box.createVerticalStrut(8));

        // Separador visual
        panelAcciones.add(new JSeparator());
        panelAcciones.add(Box.createVerticalStrut(8));

        // ── Formulario reunión ──
        panelAcciones.add(new JLabel("── Crear reunión ──"));
        panelAcciones.add(Box.createVerticalStrut(4));

        panelAcciones.add(new JLabel("Título reunión:"));
        campoTituloReunion = new JTextField();
        campoTituloReunion.setMaximumSize(new Dimension(Integer.MAX_VALUE, 26));
        panelAcciones.add(campoTituloReunion);
        panelAcciones.add(Box.createVerticalStrut(4));

        panelAcciones.add(new JLabel("Fecha (ej. 2026-06-10):"));
        campoFechaReunion = new JTextField();
        campoFechaReunion.setMaximumSize(new Dimension(Integer.MAX_VALUE, 26));
        panelAcciones.add(campoFechaReunion);
        panelAcciones.add(Box.createVerticalStrut(4));

        panelAcciones.add(new JLabel("Plataforma (ej. Teams):"));
        campoPlataformaReunion = new JTextField("Teams");
        campoPlataformaReunion.setMaximumSize(new Dimension(Integer.MAX_VALUE, 26));
        panelAcciones.add(campoPlataformaReunion);
        panelAcciones.add(Box.createVerticalStrut(6));

        panelAcciones.add(boton("Crear reunión", e -> accionCrearReunion()));
        panelAcciones.add(Box.createVerticalStrut(8));

        // Separador visual
        panelAcciones.add(new JSeparator());
        panelAcciones.add(Box.createVerticalStrut(8));

        // ── Otros botones ──
        panelAcciones.add(boton("Avanzar estado tarea",  e -> {
            controlador.cambiarEstadoTareaDesdeVista();
            estado("Estado de tarea cambiado");
        }));
        panelAcciones.add(Box.createVerticalStrut(4));
        panelAcciones.add(boton("Resumen básico",         e -> {
            controlador.ejecutarEstrategiaBasicaDesdeVista();
            log("STRATEGY", "Resumen básico ejecutado");
        }));
        panelAcciones.add(Box.createVerticalStrut(4));
        panelAcciones.add(boton("Análisis avanzado",      e -> {
            controlador.ejecutarEstrategiaAvanzadaDesdeVista();
            log("STRATEGY", "Análisis avanzado ejecutado");
        }));
        panelAcciones.add(Box.createVerticalStrut(4));
        panelAcciones.add(boton("Demo completa",          e -> {
            String reporte = controlador.ejecutarDemoCompletaPatrones();
            log("DEMO", reporte);
        }));
        panelAcciones.add(Box.createVerticalStrut(4));
        panelAcciones.add(boton("Limpiar",                e -> {
            areaEventos.setText("");
            estado("Panel limpiado");
        }));

        panelAcciones.add(Box.createVerticalGlue());

        // ── Panel derecho: log de eventos ──
        areaEventos = new JTextArea();
        areaEventos.setEditable(false);
        areaEventos.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scroll = new JScrollPane(areaEventos);
        scroll.setBorder(BorderFactory.createTitledBorder("Eventos del sistema"));

        // ── Barra de estado inferior ──
        etiquetaEstado = new JLabel("Sistema activo");
        etiquetaEstado.setBorder(BorderFactory.createEmptyBorder(2, 4, 2, 4));

        raiz.add(panelAcciones, BorderLayout.WEST);
        raiz.add(scroll, BorderLayout.CENTER);
        raiz.add(etiquetaEstado, BorderLayout.SOUTH);
        add(raiz);
    }

    // ── Helpers ──────────────────────────────────────────────────

    private JButton boton(String texto, java.awt.event.ActionListener listener) {
        JButton b = new JButton(texto);
        b.setAlignmentX(Component.LEFT_ALIGNMENT);
        b.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        b.addActionListener(listener);
        return b;
    }

    private void accionConNombre(String tipo) {
        String nombre = campoNombre.getText().trim();
        switch (tipo) {
            case "tarea":
                controlador.crearTareaDesdeVista(nombre);
                estado("Tarea creada" + (nombre.isEmpty() ? "" : ": " + nombre));
                break;
            case "bug":
                controlador.crearBugDesdeVista(nombre);
                estado("Bug creado" + (nombre.isEmpty() ? "" : ": " + nombre));
                break;
            case "funcionalidad":
                controlador.crearFuncionalidadDesdeVista(nombre);
                estado("Funcionalidad creada" + (nombre.isEmpty() ? "" : ": " + nombre));
                break;
            case "proyecto":
                controlador.crearProyectoDesdeVista(nombre);
                estado("Proyecto creado" + (nombre.isEmpty() ? "" : ": " + nombre));
                break;
        }
        campoNombre.setText("");
    }

    private void accionCrearReunion() {
        String titulo     = campoTituloReunion.getText().trim();
        String fecha      = campoFechaReunion.getText().trim();
        String plataforma = campoPlataformaReunion.getText().trim();

        if (titulo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingresa un título para la reunión.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Reunion reunion = controlador.crearReunionDesdeVista(titulo, fecha, plataforma);
        estado("Reunión creada: " + reunion.getTitulo());

        campoTituloReunion.setText("");
        campoFechaReunion.setText("");
    }

    private void log(String tag, String mensaje) {
        String hora = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        areaEventos.append("[" + hora + "] [" + tag + "]\n" + mensaje + "\n" +
                "────────────────────────────────────────\n");
        areaEventos.setCaretPosition(areaEventos.getDocument().getLength());
    }

    private void estado(String texto) {
        etiquetaEstado.setText(texto);
    }

    // ── Observer ─────────────────────────────────────────────────

    @Override
    public void update(String evento, Object data) {
        SwingUtilities.invokeLater(() -> log(evento, String.valueOf(data)));
    }
}