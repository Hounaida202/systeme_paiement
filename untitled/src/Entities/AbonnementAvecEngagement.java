package Entities;

public class AbonnementAvecEngagement extends Abonnement{
 private String dureeEngagementMois;

    public AbonnementAvecEngagement(String idAbonnement,
                                    String nomService,
                                    double montantMensuel,
                                    String dateDebut,
                                    String dateFin,
                                    String statut,
                                    String dureeEngagementMois) {

        super(idAbonnement, nomService, montantMensuel, dateDebut, dateFin, statut);
        this.dureeEngagementMois = dureeEngagementMois;
    }
}
