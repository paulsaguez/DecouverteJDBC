package fr.upjv;

import java.sql.*;

public class IntroductionJDBC {

    public static void main(String[] args) {
        // 1ère étape
        String nomDriverJdbcDuSGBD="org.postgresql.Driver";

        // 2ème étape
        String urlDB="jdbc:postgresql://localhost:5432/mabellebase";

        // 3ème étape : charger en mémoire le driver JDBC
        try {
            Class.forName(nomDriverJdbcDuSGBD);
            System.out.println("Chargement du Driver JDBC : OK");
        } catch (ClassNotFoundException e) {
            System.out.println("Chargement du Driver JDBC : Echec");
            e.printStackTrace();
            System.exit(-3);
        }

        // 4ème étape : création du tuyau de communication entre programme Java et SGBD
        Connection cnx = null;

        try {
            cnx = DriverManager.getConnection(urlDB, "utilisateur", "pwd");
            System.out.println("Connexion : OK");

        } catch (SQLException e) {
            System.out.println("Connexion : OK");
            e.printStackTrace();
            System.exit(-4);

        }

        // Requêtes sans "Select"
        Statement stmt = null;

        try {
            stmt=cnx.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String requeteSansSelect = "CREATE TABLE IF NOT EXISTS mabelletable (Nom VARCHAR(256),DateNais DATE);";
        try {
            stmt.executeUpdate(requeteSansSelect);
            System.out.println("Create table mabelletable : OK");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        requeteSansSelect = "INSERT INTO mabelletable VALUES ('Bob', '2025-10-24')";
        try {
            stmt.executeUpdate(requeteSansSelect);
            System.out.println("Insertion : OK");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Requêtes avec "Select"
        String requeteAvecSelect = "SELECT * FROM mabelletable";

        try {
            ResultSet resultatSet = stmt.executeQuery(requeteAvecSelect);
            System.out.println("Requête : OK");
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-6);
        }

        // fin
        try {
            stmt.close();
            cnx.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
