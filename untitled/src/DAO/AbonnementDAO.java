
package DAO;

import Entities.Abonnement;
import Entities.AbonnementAvecEngagement;
import Entities.AbonnementSansEngagement;
import Utilitaire.Database;
import Utilitaire.validation;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

public class AbonnementDAO {

    public static void creerAbonnement(Abonnement abonnement) {
        boolean avecEngagement = abonnement instanceof AbonnementAvecEngagement;

        String sql;

        if (avecEngagement) {
            sql = "INSERT INTO abonnements " +
                    "(id_abonnement, nom_service, montant_mensuel, date_debut, date_fin, statut, type_abonnement, dureeengagementmois) " +
                    "VALUES (?,?,?,?,?,?,?,?)";
        } else {
            sql = "INSERT INTO abonnements " +
                    "(id_abonnement, nom_service, montant_mensuel, date_debut, date_fin, statut, type_abonnement) " +
                    "VALUES (?,?,?,?,?,?,?)";
        }

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setObject(1, UUID.fromString(abonnement.getIdAbonnement()));  // UUID
            stmt.setString(2, abonnement.getNomService());
            stmt.setDouble(3, abonnement.getMontantMensuel());
            stmt.setDate(4, Date.valueOf(abonnement.getDateDebut()));
            stmt.setDate(5, Date.valueOf(abonnement.getDateFin()));
            stmt.setString(6, abonnement.getStatut().name());
            stmt.setString(7, abonnement.getTypeAbonnement());

            if (avecEngagement) {
                AbonnementAvecEngagement a = (AbonnementAvecEngagement) abonnement;
                stmt.setObject(8, a.getDureeEngagementMois());
            }

            stmt.executeUpdate();

            System.out.println("Abonnement inséré avec succès dans la base !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void supprimerAbonnement(String idAbonnement) {
        String sql = "DELETE FROM abonnements WHERE id_abonnement = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
           boolean id = validation.siAbonnementExiste(idAbonnement);
            if (id == true) {
                stmt.setObject(1, UUID.fromString(idAbonnement));
                stmt.executeUpdate();
                System.out.println("abonnement supprimer");
            }
            else {
                System.out.println("Abonnement non existe ");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public static Abonnement getAboById(String idAbonnement) {
        String sql = "SELECT * FROM abonnements WHERE id_abonnement = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setObject(1, UUID.fromString(idAbonnement));
            ResultSet res = stmt.executeQuery();

            if (res.next()) {
                String nom = res.getString("nom_service");
                double montantMensuel = res.getDouble("montant_mensuel");
                String dateDebut = res.getDate("date_debut").toString();
                String dateFin = res.getDate("date_fin").toString();
                String typeAbonnement = res.getString("type_abonnement");

                Abonnement abo;
                if (typeAbonnement.equals("Abonnement Avec Engagement")) {
                    int duree = res.getInt("dureeengagementmois");
                    abo = new AbonnementAvecEngagement(idAbonnement, nom, montantMensuel, dateDebut, dateFin, typeAbonnement, duree);
                } else {
                    abo = new AbonnementSansEngagement(idAbonnement, nom, montantMensuel, dateDebut, dateFin, typeAbonnement);
                }

                return abo;
            } else {
                System.out.println("L'abonnement n'existe pas.");
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }







    public static void modifierAbonnement(Abonnement abonnement) {
        String sql;

        if(abonnement instanceof AbonnementAvecEngagement) {
            sql = "UPDATE abonnements SET nom_service = ?, montant_mensuel = ?, date_debut = ?, date_fin = ?, type_abonnement = ?, dureeengagementmois = ? " +
                    "WHERE id_abonnement = ?";
        } else {
            sql = "UPDATE abonnements SET nom_service = ?, montant_mensuel = ?, date_debut = ?, date_fin = ?, type_abonnement = ? " +
                    "WHERE id_abonnement = ?";
        }

        try {
            Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, abonnement.getNomService());
            stmt.setDouble(2, abonnement.getMontantMensuel());
            stmt.setDate(3, Date.valueOf(abonnement.getDateDebut()));
            stmt.setDate(4, Date.valueOf(abonnement.getDateFin()));
            stmt.setString(5, abonnement.getTypeAbonnement());

            if (abonnement instanceof AbonnementAvecEngagement) {
                AbonnementAvecEngagement a = (AbonnementAvecEngagement) abonnement;
                stmt.setInt(6, a.getDureeEngagementMois());
                stmt.setObject(7, UUID.fromString(abonnement.getIdAbonnement()));
            } else {
                stmt.setObject(6, UUID.fromString(abonnement.getIdAbonnement()));
            }

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Abonnement>  getAllAbonnements() {
        String sql = "SELECT * FROM abonnements";
        Abonnement abonnement;
        List<Abonnement> abonnements = new ArrayList<>();
        try
        {
            Connection conn = Database.getConnection();
            PreparedStatement stmt= conn.prepareStatement(sql);
            ResultSet res = stmt.executeQuery();
            while(res.next()){
                String id = res.getObject("id_abonnement").toString();
                String nom = res.getString("nom_service");
                double montantMensuel = res.getDouble("montant_mensuel");
                String dateDebut = res.getDate("date_debut").toString();
                String dateFin = res.getDate("date_fin").toString();
                String typeAbonnement = res.getString("type_abonnement");
                if(typeAbonnement.equals("Abonnement Avec Engagement")) {
                    int duree = res.getInt("dureeengagementmois");
                    AbonnementAvecEngagement abo = new AbonnementAvecEngagement(id,nom,montantMensuel,dateDebut,dateFin,typeAbonnement,duree);
                    abonnements.add(abo);
                }
                else {
                    AbonnementSansEngagement abo = new AbonnementSansEngagement(id,nom,montantMensuel,dateDebut,dateFin,typeAbonnement);
                    abonnements.add(abo);
                }
            }
            return abonnements;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }













}










