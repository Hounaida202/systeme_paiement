
package Presentation;

import java.util.Scanner;

import DAO.AbonnementDAO;
import Entities.Paiement;
import Utilitaire.validation;
import Metier.*;

public class Menu {

    private Scanner scanner;

    public Menu() {
        scanner = new Scanner(System.in);
    }
    public void afficherMenu() {
        int choix;
        do {
            System.out.println("1-Créer des abonnements");

            System.out.println("2 - supprimer un abonnement");
            System.out.println("3 - modifier un abonnement");
            System.out.println("4 - afficher toute les abonnements:");
            System.out.println("5 - les paiements");
            System.out.println("6 - enregistrer un paiement");
            System.out.println("7 - afficher les paiements manqué");
            System.out.println("8 - afficher les 5 derniers paiements ");

            System.out.println("9- quitter");
            choix = validation.verifierchoix(scanner, 1, 9);
            AbonnementService as=new AbonnementService();
            PaiementService ps=new PaiementService();

            switch (choix) {
                case 1:
                    as.creerAbonementt();
                    break;
                case 2:
                    as.supprimerabenementt();
                    break;
                case 3:
                    as.modifierabenementt();
                    break;
                case 4:
                    as.afficherlesabos();
                    break;
                case 5:
                    ps.afficherpaiements();
                    break;
                case 6:
                    ps.enregitrerPaiement();
                    break;
                case 7:
                    ps.paiementmanqué();
                    break;
                case 8:
                    ps.Dernierpaiements();
                    break;
                case 9:
                    System.out.println("programme exité");
                    break;
            }
        } while (choix != 9);
    }

}
