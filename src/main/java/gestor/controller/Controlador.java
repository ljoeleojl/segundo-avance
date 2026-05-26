package gestor.controller;

import gestor.interfaces.FabricaGestor;
import gestor.interfaces.Mediador;
import gestor.singleton.GestorPrincipalSingleton;

public class Controlador {

    private FabricaGestor estrategia;

    public Controlador() {}

    public void setEstrategia(FabricaGestor estrategia) {
        this.estrategia = estrategia;
    }

    public void gestionDeProyecto() {
        if (estrategia == null) {
            System.out.println("[Controlador] No hay estrategia definida");
            return;
        }
        GestorPrincipalSingleton gestor = GestorPrincipalSingleton.getInstancia();
        estrategia.setMediador(gestor);
        System.out.println("[Controlador] Estrategia ejecutada con el gestor principal");
    }

    public void ejecutar(GestorPrincipalSingleton gestor) {
        System.out.println("[Controlador] Ejecutando con gestor: " + gestor);
        gestionDeProyecto();
    }
}
