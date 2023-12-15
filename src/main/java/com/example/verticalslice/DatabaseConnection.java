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

    public static void updateCursusArray() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(connectionUrl);
            String SQL = "SELECT * FROM Cursus";
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
        } catch (Exception e) {
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


