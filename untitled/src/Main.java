import DAO.AbonnementDAO;
import Entities.Abonnement;
import Presentation.Menu;
import Utilitaire.Database;
import Utilitaire.validation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {



         System.out.println("menu");
         Menu menu = new Menu();
         menu.afficherMenu();
    }
}

