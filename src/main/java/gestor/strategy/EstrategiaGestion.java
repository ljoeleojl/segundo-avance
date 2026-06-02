package gestor.strategy;

import gestor.singleton.GestorPrincipalSingleton;

public interface EstrategiaGestion {
    void ejecutar(GestorPrincipalSingleton gestor);
}