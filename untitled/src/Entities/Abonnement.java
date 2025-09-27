
package Entities;

public abstract class Abonnement {
    private String idAbonnement;
    private String nomService;
    private double montantMensuel;
    private String dateDebut;
    private String dateFin;
    private Statut statut;
    private String typeAbonnement;

    public enum Statut {
        ACTIVE, SUSPENDU, RESILIE
    }

    // Constructeur principal
    public Abonnement(String idAbonnement,
                      String nomService,
                      double montantMensuel,
                      String dateDebut,
                      String dateFin,
                      String typeAbonnement) {
        this.idAbonnement = idAbonnement;
        this.nomService = nomService;
        this.montantMensuel = montantMensuel;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.typeAbonnement = typeAbonnement;
        this.statut = Statut.ACTIVE; // Tout nouvel abonnement est actif par défaut
    }

    // ===== Getters et Setters =====
    public String getIdAbonnement() { return idAbonnement; }
    public void setIdAbonnement(String idAbonnement) { this.idAbonnement = idAbonnement; }

    public String getNomService() { return nomService; }
    public void setNomService(String nomService) { this.nomService = nomService; }

    public double getMontantMensuel() { return montantMensuel; }
    public void setMontantMensuel(double montantMensuel) { this.montantMensuel = montantMensuel; }

    public String getDateDebut() { return dateDebut; }
    public void setDateDebut(String dateDebut) { this.dateDebut = dateDebut; }

    public String getDateFin() { return dateFin; }
    public void setDateFin(String dateFin) { this.dateFin = dateFin; }

    public Statut getStatut() { return statut; }
    public void setStatut(Statut statut) { this.statut = statut; }

    public String getTypeAbonnement() { return typeAbonnement; }
    public void setTypeAbonnement(String typeAbonnement) { this.typeAbonnement = typeAbonnement; }
}
