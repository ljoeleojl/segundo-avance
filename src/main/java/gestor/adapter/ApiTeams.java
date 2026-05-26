package gestor.adapter;

public class ApiTeams {

    public String generarLinkTeams() {
        String link = "https://teams.microsoft.com/meet/" + System.currentTimeMillis();
        System.out.println("[ApiTeams] Link generado: " + link);
        return link;
    }

    public void iniciarReunion() {
        System.out.println("[ApiTeams] Reunión iniciada en Microsoft Teams");
    }
}
