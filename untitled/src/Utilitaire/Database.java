package Utilitaire;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

        private static final String URL = "jdbc:postgresql://localhost:5432/s-p";
        private static final String USER = "postgres";
        private static final String PASS = "etrichi";

        public static Connection getConnection() throws SQLException {
            return DriverManager.getConnection(URL, USER, PASS);
        }
    }

