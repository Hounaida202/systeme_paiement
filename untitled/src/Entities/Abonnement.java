package Entities;

public abstract class Abonnement {
  private String idAbonnement;
  private String nomService;
  private double montantMensuel;
  private String dateDebut;
  private String dateFin;
  private enum statut {active,suspendu,resilie};

}
