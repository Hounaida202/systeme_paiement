
package Entities;

public class AbonnementSansEngagement extends Abonnement{
    public AbonnementSansEngagement(String idAbonnement,
                                    String nomService,
                                    double montantMensuel,
                                    String dateDebut,
                                    String dateFin,
                                    String typeAbonnement

    ) {

        super(idAbonnement, nomService, montantMensuel, dateDebut, dateFin, typeAbonnement);
    }
}
