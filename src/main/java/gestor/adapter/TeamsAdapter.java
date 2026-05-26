package gestor.adapter;

import gestor.interfaces.Plataforma;

public class TeamsAdapter implements Plataforma {

    private final ApiTeams apiTeams;

    public TeamsAdapter() {
        this.apiTeams = new ApiTeams();
    }

    @Override
    public String crearEnlace() {
        return apiTeams.generarLinkTeams();
    }

    @Override
    public void iniciarSesion() {
        apiTeams.iniciarReunion();
    }
}
