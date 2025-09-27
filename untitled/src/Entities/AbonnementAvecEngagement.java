
package Entities;

public class AbonnementAvecEngagement extends Abonnement {
    private int dureeEngagementMois;

    public AbonnementAvecEngagement(String idAbonnement,
                                    String nomService,
                                    double montantMensuel,
                                    String dateDebut,
                                    String dateFin,
                                    String typeAbonnement,
                                    int dureeEngagementMois) {
        super(idAbonnement, nomService, montantMensuel, dateDebut, dateFin, typeAbonnement);
        this.dureeEngagementMois = dureeEngagementMois;
    }
    public int getDureeEngagementMois() {
        return dureeEngagementMois;
    }
    public void setDureeEngagementMois(int dureeEngagementMois) {
        this.dureeEngagementMois = dureeEngagementMois;
    }
}

