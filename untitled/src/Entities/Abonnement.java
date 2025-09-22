package Entities;

public abstract class Abonnement {
  private String idAbonnement;
  private String nomService;
  private double montantMensuel;
  private String dateDebut;
  private String dateFin;
  private String statut;

  public enum statut {active,suspendu,resilie};

  public Abonnement(String idAbonnement,
                    String nomService,
                    double montantMensuel,
                    String dateDebut,
                    String dateFin,
                    String statut) {

      this.idAbonnement = idAbonnement;
      this.nomService = nomService;
      this.montantMensuel = montantMensuel;
      this.dateDebut = dateDebut;
      this.dateFin = dateFin;
      this.statut=statut;

  }

}
