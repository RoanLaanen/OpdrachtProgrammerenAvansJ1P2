//package com.example.verticalslice;
//
//import java.sql.*;
//public class DatabaseConnection {
//
//    public static String introductieTekst;
//
//    public static void main(String[] args) {
//
//        // Dit zijn de instellingen voor de verbinding. Vervang de databaseName indien deze voor jou anders is.
//        String connectionUrl = "jdbc:sqlserver://localhost;databaseName=Codecadamy;integratedSecurity=true;encrypt=true;trustServerCertificate=true;";
//
//        // Connection beheert informatie over de connectie met de database.
//        Connection con = null;
//
//        // Statement zorgt dat we een SQL query kunnen uitvoeren.
//        Statement stmt = null;
//
//        // ResultSet is de tabel die we van de database terugkrijgen.
//        // We kunnen door de rows heen stappen en iedere kolom lezen.
//        ResultSet rs = null;
//        try {
//            // 'Importeer' de driver die je gedownload hebt.
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            // Maak de verbinding met de database.
//            con = DriverManager.getConnection(connectionUrl);
//
//            String SQL = "SELECT TOP 1 * FROM Cursus";
//            stmt = con.createStatement();
//            rs = stmt.executeQuery(SQL);
//
//            // Als de resultset waarden bevat dan lopen we hier door deze waarden en printen ze.
//            while (rs.next()) {
//
//                String cursusNaam = rs.getString("cursusNaam");
//                introductieTekst = rs.getString("introductieTekst");
//                String onderwerp = rs.getString("onderwerp");
//
//                System.out.format("\n  -  %-50s  -  %-80s  -  %-50s\n", cursusNaam, introductieTekst, onderwerp);
//
//            }
//        }
//
//        // Handle any errors that may have occurred.
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//        finally {
//            if (rs != null) try { rs.close(); } catch(Exception e) {}
//            if (stmt != null) try { stmt.close(); } catch(Exception e) {}
//            if (con != null) try { con.close(); } catch(Exception e) {}
//        }
//
//    }
//
//
//}

package com.example.verticalslice;

import java.sql.*;

public class DatabaseConnection {

    public static String introductieTekst;

    // Constructor to initialize the database connection and retrieve data
    public DatabaseConnection() {
        initializeDatabaseConnection();
    }

    public void initializeDatabaseConnection() {
        // Dit zijn de instellingen voor de verbinding. Vervang de databaseName indien deze voor jou anders is.
        String connectionUrl = "jdbc:sqlserver://localhost;databaseName=Codecadamy;integratedSecurity=true;encrypt=true;trustServerCertificate=true;";

        // Connection beheert informatie over de connectie met de database.
        Connection con = null;

        // Statement zorgt dat we een SQL query kunnen uitvoeren.
        Statement stmt = null;

        // ResultSet is de tabel die we van de database terugkrijgen.
        // We kunnen door de rows heen stappen en iedere kolom lezen.
        ResultSet rs = null;
        try {
            // 'Importeer' de driver die je gedownload hebt.
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            // Maak de verbinding met de database.
            con = DriverManager.getConnection(connectionUrl);

            String SQL = "SELECT TOP 1 * FROM Cursus";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);

            // Als de resultset waarden bevat dan lopen we hier door deze waarden en printen ze.
            while (rs.next()) {
                String cursusNaam = rs.getString("cursusNaam");
                introductieTekst = rs.getString("introductieTekst");
                String onderwerp = rs.getString("onderwerp");

//                System.out.format("\n  -  %-50s  -  %-80s  -  %-50s\n", cursusNaam, introductieTekst, onderwerp);
            }
        }

        // Handle any errors that may have occurred.
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) try { rs.close(); } catch(Exception e) {}
            if (stmt != null) try { stmt.close(); } catch(Exception e) {}
            if (con != null) try { con.close(); } catch(Exception e) {}
        }
    }
}
