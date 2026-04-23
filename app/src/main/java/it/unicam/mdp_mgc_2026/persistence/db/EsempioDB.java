package it.unicam.mdp_mgc_2026.persistence.db;

import java.sql.*;

public class EsempioDB {

    private static final String URL = "jdbc:postgresql://localhost:3305/testdb";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    public static void connettiEStampa() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Connessione riuscita!");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users;");

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                System.out.println("ID: " + id + ", Name: " + name);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        connettiEStampa();
    }
}
