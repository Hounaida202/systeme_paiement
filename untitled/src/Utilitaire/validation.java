package Utilitaire;

import Utilitaire.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.UUID;
import Entities.Abonnement;


import java.util.Scanner;public class validation {
    public static int verifierchoix(Scanner scanner, int min, int max) {
        int choix;
        while (true) {
            System.out.print("Choix : ");
            if (scanner.hasNextInt()) {
                choix = scanner.nextInt();
                scanner.nextLine();
                if (choix >= min && choix <= max) {
                    return choix;
                }
            } else {
                scanner.nextLine();
            }
            System.out.println("choix invalide");
        }
    }

public static boolean siAbonnementExiste(String idAbonnement) {
    String sql = "SELECT count(*) as total FROM abonnements WHERE id_abonnement = ?";

    try (Connection conn = Database.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setObject(1, UUID.fromString(idAbonnement));
        ResultSet resultat = stmt.executeQuery();

        if (resultat.next()) {
            int count = resultat.getInt("total");
            if (count >= 1) {
                return
                        true;
            } else
                return false;
        }


    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}
}
