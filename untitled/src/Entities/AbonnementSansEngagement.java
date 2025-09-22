package Entities;

public class AbonnementSansEngagement extends Abonnement{
    public AbonnementSansEngagement(String idAbonnement,
                                    String nomService,
                                    double montantMensuel,
                                    String dateDebut,
                                    String dateFin,
                                    Statut statut
                                    ) {

        super(idAbonnement, nomService, montantMensuel, dateDebut, dateFin, statut);
    }
}
