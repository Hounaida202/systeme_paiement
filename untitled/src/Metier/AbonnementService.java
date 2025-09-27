package Metier;


import Entities.Abonnement;
import Entities.Paiement;

import Entities.AbonnementAvecEngagement;
import Entities.AbonnementSansEngagement;
import DAO.AbonnementDAO;
import DAO.PaiementDAO;

import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AbonnementService {

    public void creerAbonementt() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Veuillez entrer le nom de l'abonnement :");
        String nom = sc.nextLine();

        System.out.println("Choisir le type de votre abonnement :");
        System.out.println("1 - Abonnement Sans Engagement");
        System.out.println("2 - Abonnement Avec Engagement");
        int type = sc.nextInt();
        sc.nextLine();

        System.out.println("Choisir le montant mensuel à payer en DH :");
        double montant = sc.nextDouble();
        sc.nextLine();

        System.out.println("Choisir la date de début (format yyyy-MM-dd) :");
        String dateDebutStr = sc.nextLine();
        LocalDate dateDebut = LocalDate.parse(dateDebutStr);
        System.out.println("Veuillez entrer le type de paiement :");
        String typePaiement = sc.nextLine();
        Abonnement abo;
        String typeAbonnement;
        LocalDate dateFin;
        int duree = 0;
        if (type == 1) {
            typeAbonnement = "Abonnement Sans Engagement";
            System.out.println("La date de fin sera après combien de mois :");
            duree = sc.nextInt();
            dateFin = dateDebut.plusMonths(duree);

            abo = new AbonnementSansEngagement(
                    UUID.randomUUID().toString(),
                    nom,
                    montant,
                    dateDebut.toString(),
                    dateFin.toString(),
                    typeAbonnement
            );

        } else {
            typeAbonnement = "Abonnement Avec Engagement";
            System.out.println("Veuillez entrer la durée de l'engagement en mois :");
            duree = sc.nextInt();
            sc.nextLine();
            dateFin = dateDebut.plusMonths(duree);

            abo = new AbonnementAvecEngagement(
                    UUID.randomUUID().toString(),
                    nom,
                    montant,
                    dateDebut.toString(),
                    dateFin.toString(),
                    typeAbonnement,
                    duree
            );
        }
        AbonnementDAO.creerAbonnement(abo);
        Paiement premierPaiement = new Paiement(
                UUID.randomUUID().toString(),
                abo.getIdAbonnement(),
                dateDebut.plusMonths(1).toString(),
                LocalDate.now().toString(),
                typePaiement,
                Paiement.Statut.PAYE
        );
        PaiementDAO.creerUnpaiement(premierPaiement);

        for (int i = 2; i <= duree; i++) {
            Paiement paiementSuivant = new Paiement(
                    UUID.randomUUID().toString(),
                    abo.getIdAbonnement(),
                    dateDebut.plusMonths(i).toString(),
                    null,
                    typePaiement,
                    Paiement.Statut.NONPAYE
            );
            PaiementDAO.creerUnpaiement(paiementSuivant);
        }

        System.out.println(typeAbonnement + " créé avec paiements planifiés. ID: " + abo.getIdAbonnement());
    }
    public void supprimerabenementt(){
        System.out.println("veuillez entrer le code de l'abonnemnt a supprimer:");
        Scanner scan = new Scanner(System.in);
        String code = scan.nextLine();
        AbonnementDAO.supprimerAbonnement(code);
    }

    public void modifierabenementt(){
        Scanner scan = new Scanner(System.in);

        System.out.println("veuillez entrer  le code de l'abonnemnt a modifier:");
        String code = scan.nextLine();


        Abonnement abo = AbonnementDAO.getAboById(code);

        System.out.println("veuillez entrer le nouveau nom de service ");
        String nom = scan.nextLine();
        abo.setNomService(nom);

        System.out.println("Choisir le montant mensuel à payer en DH :");
        double montant = scan.nextDouble();
        scan.nextLine();
        abo.setMontantMensuel(montant);

        System.out.println("Choisir la date de début (format yyyy-MM-dd) :");
        String dateDebutStr = scan.nextLine();
        LocalDate dateDebut2 = LocalDate.parse(dateDebutStr);
        abo.setDateDebut(dateDebutStr);


        if (abo instanceof AbonnementAvecEngagement) {
            AbonnementAvecEngagement aboavec = (AbonnementAvecEngagement) abo;
            System.out.println("La date de fin sera après combien de mois :");
            int combienDeMois = scan.nextInt();
            aboavec.setDureeEngagementMois(combienDeMois);
            LocalDate nouveaudate = dateDebut2.plusMonths(combienDeMois);
            abo.setDateFin(nouveaudate.toString());

            AbonnementDAO.modifierAbonnement(aboavec);

        } else if (abo instanceof AbonnementSansEngagement) {
            AbonnementSansEngagement abosans = (AbonnementSansEngagement) abo;
            AbonnementDAO.modifierAbonnement(abosans);
        }

    }
    public static void afficherlesabos() {
        System.out.println("Vos abonnements : ");
        List<Abonnement> abonnements = AbonnementDAO.getAllAbonnements();
        for (Abonnement abo : abonnements) {
            System.out.println("ID : " + abo.getIdAbonnement());
            System.out.println("Nom du service : " + abo.getNomService());
            System.out.println("Montant mensuel : " + abo.getMontantMensuel() + " DH");
            System.out.println("Date début : " + abo.getDateDebut());
            System.out.println("Date fin : " + abo.getDateFin());
            System.out.println("Type : " + abo.getTypeAbonnement());

            if (abo instanceof AbonnementAvecEngagement) {
                AbonnementAvecEngagement aboAvec = (AbonnementAvecEngagement) abo;
                System.out.println("Durée d'engagement: " + aboAvec.getDureeEngagementMois() + " mois");
            }
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ :)");
        }

    }
}
