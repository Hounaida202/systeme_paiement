package Metier;
import Entities.Abonnement;
import Entities.Paiement;

import DAO.PaiementDAO;

import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class PaiementService {

    public static void afficherpaiements(){
        System.out.println("veuillez zntrer le code de l'abonnement");
        Scanner scan = new Scanner(System.in);
        String code = scan.nextLine();
        System.out.println("Vos paiements : ");
        PaiementDAO paiementDAO = new PaiementDAO();
        List<Paiement> paiements = paiementDAO.allPaiementsbyIDAbo(code);
        for (Paiement paiement : paiements) {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~:)");
            System.out.println("ID : " + paiement.getIdPaiement());
            System.out.println("date d'echeance : "+ paiement.getDateEcheance());
            System.out.println("date de paiement : "+ paiement.getDatePaiement());

            System.out.println("type de paiement : "+paiement.getTypePaiement());
            System.out.println("le statut de ce paiement " +paiement.getStatut());
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~:)");
        }
    }
    public void enregitrerPaiement(){

        System.out.println("veuiller entrer le code de paiement a enregistrer:");
        Scanner scan = new Scanner(System.in);
        String code = scan.nextLine();
        Paiement pi = PaiementDAO.getPaiementById(code);

        if(pi.getStatut()==Paiement.Statut.PAYE){
            System.out.println("ce paiement est deja payé");
        }


        else {
            String statut = "PAYE";
            LocalDate date = LocalDate.now();
            pi.setStatut(Paiement.Statut.valueOf(statut));
            pi.setDatePaiement(date.toString());
            PaiementDAO.modifierPaiement(pi);

            System.out.println("paiement enregistré");
        }

    }

    public static void paiementmanqué() {
        System.out.println("Les paiements non payés :");
        List<Paiement> paiements = PaiementDAO.paiementsManque();
        if (paiements.isEmpty()) {
            System.out.println("Aucun paiement non payé trouvé.");
        } else {
            for (Paiement pa : paiements) {
                System.out.println("-------------------------------");
                System.out.println("ID Paiement : " + pa.getIdPaiement());
                System.out.println("ID Abonnement : " + pa.getIdAbonnement());
                System.out.println("Date echeance : " + pa.getDateEcheance());
                System.out.println("Date paiement : " + pa.getDatePaiement());
                System.out.println("Type paiement : " + pa.getTypePaiement());
                System.out.println("Statut : " + pa.getStatut());
            }
        }

        System.out.println("le montant total a payer :"+ PaiementDAO.sommeMontant());

    }

    public static void Dernierpaiements(){
        System.out.println("Les 5 derniers paiements :");
        List<Paiement> paiements = PaiementDAO.get5DerniersPaiements();
        for(Paiement pa : paiements){
            System.out.println("-------------------------------");
            System.out.println("ID Paiement : " + pa.getIdPaiement());
            System.out.println("ID Abonnement : " + pa.getIdAbonnement());
            System.out.println("Date echeance : " + pa.getDateEcheance());
            System.out.println("Date paiement : " + pa.getDatePaiement());
            System.out.println("Type paiement : " + pa.getTypePaiement());
            System.out.println("Statut : " + pa.getStatut());
        }
    }
}
