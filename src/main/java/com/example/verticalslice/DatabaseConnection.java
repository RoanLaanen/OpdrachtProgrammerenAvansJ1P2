package com.example.verticalslice;

import java.sql.*;
import java.util.ArrayList;

/**
 * Represents a database connection.
 */
public class DatabaseConnection {
    public static ArrayList<Cursus> cursusArray = new ArrayList<>();
    public static ArrayList<String> cursusNaamArray = new ArrayList<>();
    public static String connectionUrl = "jdbc:mysql://162.19.139.137:3306/s49235_Codecademy?user=u49235_iICN9w4ctL&password=cX20vY5KOLk14Wuxp2wNr4wr";
    public static boolean loaded = false;

    public static void main(String[] args) {
        getCursussen();


    }

    public static void getCursussen() {
        // Dit zijn de instellingen voor de verbinding. Vervang de databaseName indien deze voor jou anders is.

        // Connection beheert informatie over de connectie met de database.
        Connection con = null;

        // Statement zorgt dat we een SQL query kunnen uitvoeren.
        Statement stmt = null;

        // ResultSet is de tabel die we van de database terugkrijgen.
        // We kunnen door de rows heen stappen en iedere kolom lezen.
        ResultSet rs = null;
        try {
            // 'Importeer' de driver die je gedownload hebt.
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Maak de verbinding met de database.
            con = DriverManager.getConnection(connectionUrl);

            String SQL = "SELECT * FROM Cursus";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);

            // Als de resultset waarden bevat dan lopen we hier door deze waarden en printen ze.
            while (rs.next()) {

                String cursusNaam = rs.getString("cursusNaam");
                String introductieTekst = rs.getString("introductieTekst");
                String onderwerp = rs.getString("onderwerp");
                String niveauString = rs.getString("niveau");
                Cursus.niveau niveau = Cursus.niveau.valueOf(niveauString);
                cursusArray.add(new Cursus(cursusNaam, onderwerp, introductieTekst, niveau));
                cursusNaamArray.add(cursusNaam);


            }
        }

        // Handle any errors that may have occurred.
        catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            if (rs != null) try {
                rs.close();
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
            GUI.closeConnection(con, stmt);
        }

    }


}


