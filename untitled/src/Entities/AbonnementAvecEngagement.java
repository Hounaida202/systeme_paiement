package Entities;

public class AbonnementAvecEngagement extends Abonnement {
    private String dureeEngagementMois;

    public AbonnementAvecEngagement(String idAbonnement,
                                    String nomService,
                                    double montantMensuel,
                                    String dateDebut,
                                    String dateFin,
                                    Statut statut,
                                    String dureeEngagementMois) {

        super(idAbonnement, nomService, montantMensuel, dateDebut, dateFin, statut);
        this.dureeEngagementMois = dureeEngagementMois;
    }

    // ---------- Getters et Setters ----------

    public String getDureeEngagementMois() {
        return dureeEngagementMois;
    }

    public void setDureeEngagementMois(String dureeEngagementMois) {
        this.dureeEngagementMois = dureeEngagementMois;
    }
}
