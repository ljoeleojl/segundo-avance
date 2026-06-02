package gestor;

import gestor.gui.VistaSwing;
import gestor.singleton.GestorPrincipalSingleton;
import gestor.view.Vista;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        System.out.println("====== INICIANDO SISTEMA DE GESTIÓN DE PROYECTOS ======\n");

        // Singleton: se obtiene la única instancia del gestor principal
        GestorPrincipalSingleton gestor = GestorPrincipalSingleton.getInstancia();

        System.out.println("--- [1] Singleton ---");
        System.out.println("Misma instancia: " + (gestor == GestorPrincipalSingleton.getInstancia()));

        // Se inicia la interfaz gráfica
        SwingUtilities.invokeLater(() -> {

            System.out.println("\n--- [2] MVC + Observer ---");

            // Vista de consola
            Vista vistaConsola = new Vista("Consola");

            // Vista gráfica
            VistaSwing vistaGrafica = new VistaSwing();

            // El gestor notifica a ambas vistas
            gestor.subscribe(vistaConsola);
            gestor.subscribe(vistaGrafica);

            // Mostrar interfaz
            vistaGrafica.setVisible(true);

            System.out.println("[Main] Interfaz gráfica iniciada correctamente");
            System.out.println("[Main] Use los botones para ejecutar acciones y demostrar patrones");
        });
    }
}