package gestor.strategy;

import gestor.singleton.GestorPrincipalSingleton;

public class EstrategiaGestionBasica implements EstrategiaGestion {

    @Override
    public void ejecutar(GestorPrincipalSingleton gestor) {
        System.out.println("[Strategy] Gestión básica del proyecto");
        System.out.println("[Strategy] Tarea actual: " + gestor.getTarea());
        System.out.println("[Strategy] Proyecto actual: " + gestor.getProyecto());
    }
}