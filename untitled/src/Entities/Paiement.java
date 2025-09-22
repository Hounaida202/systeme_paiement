package Entities;

public class Paiement {
   private String idPaiement;
   private String idAbonnement;
   private String dateEcheance;
   private String datePaiement;
   private String typePaiement;
   private enum statut {paye,nonpaye,retard};

}
