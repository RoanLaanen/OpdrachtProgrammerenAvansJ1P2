package com.example.verticalslice;

import java.sql.*;
import java.util.ArrayList;

/**
 * Represents a database connection.
 */
public class DatabaseConnection {
    public static ArrayList<Cursus> cursusArray = new ArrayList<>();
    public static ArrayList<String> cursusNaamArray = new ArrayList<>();
    private static String connectionUrl = "jdbc:mysql://162.19.139.137:3306/s49235_Codecademy?user=u49235_iICN9w4ctL&password=cX20vY5KOLk14Wuxp2wNr4wr";
    private static Connection con = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    public static void updateCursusArray() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(connectionUrl);
            String SQL = "SELECT * FROM Cursus ORDER BY cursusNaam ASC";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                String cursusNaam = rs.getString("cursusNaam");
                String introductieTekst = rs.getString("introductieTekst");
                String onderwerp = rs.getString("onderwerp");
                String niveauString = rs.getString("niveau");
                Cursus.niveau niveau = Cursus.niveau.valueOf(niveauString);
                cursusArray.add(new Cursus(cursusNaam, onderwerp, introductieTekst, niveau));
                cursusNaamArray.add(cursusNaam);
            }
            if (cursusArray.isEmpty()) {
                cursusArray.add(new Cursus("", "", "", Cursus.niveau.niks));
                cursusNaamArray.add("Geen resultaten gevonden");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            if (rs != null) try {
                rs.close();
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
            closeConnection(con, stmt);
        }

    }

    public static void addCursus(String cursusNaam, String onderwerp, String introductieTekst, String niveau) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(connectionUrl);
            String SQL = "INSERT INTO Cursus (cursusNaam, onderwerp, introductieTekst, niveau) VALUES ('" + cursusNaam + "', '" + onderwerp + "', '" + introductieTekst + "', '" + niveau + "')";
            stmt = con.createStatement();
            stmt.executeUpdate(SQL);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            closeConnection(con, stmt);
            updateCursusArray();
        }
    }

    public static void deleteCursus(String cursusNaam) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(connectionUrl);
            String SQL = "DELETE FROM Cursus WHERE cursusNaam = '" + cursusNaam + "'";
            stmt = con.createStatement();
            stmt.executeUpdate(SQL);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            closeConnection(con, stmt);
            updateCursusArray();
        }
    }

    private static void closeConnection(Connection con, Statement stmt) {
        if (stmt != null) try {
            stmt.close();
        } catch (Exception exception) {
            System.out.println("Error: " + exception);
        }
        if (con != null) try {
            con.close();
        } catch (Exception exception) {
            System.out.println("Error: " + exception);
        }
    }


}


