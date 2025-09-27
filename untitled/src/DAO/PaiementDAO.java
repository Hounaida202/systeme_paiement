
package DAO;

import Entities.Paiement;
import Utilitaire.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
public class PaiementDAO {

    public static void creerPaiement(Paiement paiement) {
        String sql = "INSERT INTO paiements (id_paiement, id_abonnement, date_echeance, date_paiement, type_paiement, statut) " +
                "VALUES (?, ?, ?, ?, ?, ?::statut_paiement)";


        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setObject(1, UUID.fromString(paiement.getIdPaiement()));
            stmt.setObject(2, UUID.fromString(paiement.getIdAbonnement()));
            stmt.setDate(3, Date.valueOf(paiement.getDateEcheance()));

            if (paiement.getDatePaiement() != null) {

                stmt.setDate(4, Date.valueOf(paiement.getDatePaiement()));
            } else {
                stmt.setNull(4, java.sql.Types.DATE);
            }

            stmt.setString(5, paiement.getTypePaiement());
            stmt.setString(6, paiement.getStatut().name());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void creerUnpaiement(Paiement paiement) {
        String sql = "INSERT INTO paiements (id_paiement, id_abonnement, date_echeance, date_paiement, type_paiement, statut) " +
                "VALUES (?,?,?,?,?,?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setObject(1, UUID.fromString(paiement.getIdPaiement()));
            stmt.setObject(2, UUID.fromString(paiement.getIdAbonnement()));
            stmt.setDate(3, Date.valueOf(paiement.getDateEcheance()));

            if (paiement.getDatePaiement() != null) {
                stmt.setDate(4, Date.valueOf(paiement.getDatePaiement()));
            } else {
                stmt.setNull(4, java.sql.Types.DATE);
            }

            stmt.setString(5, paiement.getTypePaiement());
            stmt.setString(6, paiement.getStatut().name());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Paiement> allPaiementsbyIDAbo(String id_abonnement) {
        String sql = "SELECT * FROM paiements WHERE id_abonnement = ?";
        List<Paiement> paiements = new ArrayList<>();

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setObject(1, UUID.fromString(id_abonnement));
            ResultSet res = stmt.executeQuery();

            while (res.next()) {
                String id = res.getObject("id_paiement").toString();
                String date_echeance = res.getDate("date_echeance").toString();
                String date_paiement = null;
                if (res.getDate("date_paiement") != null) {
                    date_paiement = res.getDate("date_paiement").toString();
                }

                String type_paiement = res.getString("type_paiement");
                String statutStr = res.getString("statut");
                Paiement.Statut statut = Paiement.Statut.valueOf(statutStr);

                Paiement paiement = new Paiement(
                        id,
                        id_abonnement,
                        date_echeance,
                        date_paiement,
                        type_paiement,
                        statut
                );

                paiements.add(paiement);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return paiements;
    }

    public static Paiement getPaiementById(String id_paiement){
        String sql = "SELECT * FROM paiements WHERE id_paiement = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setObject(1, UUID.fromString(id_paiement));
            ResultSet res = stmt.executeQuery();

            if (res.next()) {
                String id = res.getObject("id_paiement").toString();
                String id_abo = res.getObject("id_abonnement").toString();

                String date_echeance = res.getDate("date_echeance").toString();
                String date_paiement = null;
                if (res.getDate("date_paiement") != null) {
                    date_paiement = res.getDate("date_paiement").toString();
                }

                String type_paiement = res.getString("type_paiement");
                String statutStr = res.getString("statut");
                Paiement.Statut statut = Paiement.Statut.valueOf(statutStr);

                return new Paiement(
                        id,
                        id_abo,
                        date_echeance,
                        date_paiement,
                        type_paiement,
                        statut
                );
            } else {
                System.out.println("Le paiement n'existe pas.");
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void modifierPaiement(Paiement paiement) {
        String sql = "UPDATE paiements SET date_paiement = ?, statut = ? WHERE id_paiement = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (paiement.getDatePaiement() != null) {
                stmt.setDate(1, java.sql.Date.valueOf(paiement.getDatePaiement()));
            } else {
                stmt.setNull(1, java.sql.Types.DATE);
            }

            stmt.setString(2, paiement.getStatut().name());
            stmt.setObject(3, UUID.fromString(paiement.getIdPaiement()));

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la modification du paiement");
        }
    }

    public static List<Paiement> paiementsManque() {
        String sql = "SELECT * FROM paiements WHERE statut = 'NONPAYE'";
        List<Paiement> paiements = new ArrayList<>();

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String id = rs.getObject("id_paiement").toString();
                String idAbo = rs.getObject("id_abonnement").toString();
                String dateEcheance = rs.getDate("date_echeance").toString();
                String datePaiement = null;
                if (rs.getDate("date_paiement") != null) {
                    datePaiement = rs.getDate("date_paiement").toString();
                }

                String typePaiement = rs.getString("type_paiement");
                String statutStr = rs.getString("statut");
                Paiement.Statut statut = Paiement.Statut.valueOf(statutStr);
                Paiement paiement = new Paiement(id, idAbo, dateEcheance,datePaiement, typePaiement, statut);
                paiements.add(paiement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paiements;
    }

    public static double sommeMontant() {
        String sql = "SELECT SUM(a.montant_mensuel) AS total_nonpaye " +
                "FROM abonnements a " +
                "JOIN paiements p ON a.id_abonnement = p.id_abonnement " +
                "WHERE p.statut = 'NONPAYE'";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("total_nonpaye");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public static List<Paiement> get5DerniersPaiements() {
        String sql = "SELECT * FROM paiements ORDER BY date_paiement DESC LIMIT 5";
        List<Paiement> paiements = new ArrayList<>();

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String id = rs.getString("id_paiement");
                String idAbo = rs.getString("id_abonnement");
                String dateEcheance = rs.getString("date_echeance");
                String datePaiement = rs.getString("date_paiement");
                String typePaiement = rs.getString("type_paiement");
                String statutStr = rs.getString("statut");
                Paiement.Statut statut = Paiement.Statut.valueOf(statutStr);

                Paiement paiement = new Paiement(
                        id,
                        idAbo,
                        dateEcheance,
                        datePaiement,
                        typePaiement,
                        statut
                );
                paiements.add(paiement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return paiements;
    }







}
