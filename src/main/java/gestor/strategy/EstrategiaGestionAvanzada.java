package gestor.strategy;

import gestor.singleton.GestorPrincipalSingleton;

public class EstrategiaGestionAvanzada implements EstrategiaGestion {

    @Override
    public void ejecutar(GestorPrincipalSingleton gestor) {
        System.out.println("[Strategy] Gestión avanzada del proyecto");
        System.out.println("[Strategy] Bug actual: " + gestor.getBug());
        System.out.println("[Strategy] Funcionalidad actual: " + gestor.getFunc());
        System.out.println("[Strategy] Usuario actual: " + gestor.getUsuario());
    }
}