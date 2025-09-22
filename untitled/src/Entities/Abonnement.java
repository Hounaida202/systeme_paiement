package Entities;

public abstract class Abonnement {
    private String idAbonnement;
    private String nomService;
    private double montantMensuel;
    private String dateDebut;
    private String dateFin;
    private Statut statut; // utilisation de l'enum comme type

    public enum Statut {
        ACTIVE, SUSPENDU, RESILIE
    }

    public Abonnement(String idAbonnement,
                      String nomService,
                      double montantMensuel,
                      String dateDebut,
                      String dateFin,
                      Statut statut) {

        this.idAbonnement = idAbonnement;
        this.nomService = nomService;
        this.montantMensuel = montantMensuel;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.statut = statut;
    }


    public String getIdAbonnement() {
        return idAbonnement;
    }

    public void setIdAbonnement(String idAbonnement) {
        this.idAbonnement = idAbonnement;
    }

    public String getNomService() {
        return nomService;
    }

    public void setNomService(String nomService) {
        this.nomService = nomService;
    }

    public double getMontantMensuel() {
        return montantMensuel;
    }

    public void setMontantMensuel(double montantMensuel) {
        this.montantMensuel = montantMensuel;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }
}
