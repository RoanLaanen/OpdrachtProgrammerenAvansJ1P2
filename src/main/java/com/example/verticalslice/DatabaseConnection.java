package com.example.verticalslice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseConnection {
    public static ArrayList<Course> cursusArray = new ArrayList<>();
    public static ArrayList<String> cursusNaamArray = new ArrayList<>();
    private static final String connectionUrl = "jdbc:mysql://162.19.139.137:3306/s49235_Codecademy?user=u49235_iICN9w4ctL&password=cX20vY5KOLk14Wuxp2wNr4wr";
    private static Connection con = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;

    /*
    Deze methode wordt aangeroepen in GUI.java
    Deze methode zorgt ervoor dat de cursusArray en cursusNaamArray gevuld worden met de cursussen uit de database
    Deze methode wordt ook aangeroepen als er een cursus wordt toegevoegd, verwijderd of aangepast
    */
    public static void updateCursusArray() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(connectionUrl);
            String SQL = "SELECT * FROM course ORDER BY courseName ASC";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                String courseName = rs.getString("courseName");
                String topic = rs.getString("topic");
                String introText = rs.getString("introText");
                String level = rs.getString("level");
                Course.levelEnum levelValue = Course.levelEnum.valueOf(level);
                cursusArray.add(new Course(courseName, topic, introText, levelValue));
                cursusNaamArray.add(courseName);
            }
            if (cursusArray.isEmpty()) {
                cursusArray.add(new Course("", "", "", Course.levelEnum.None));
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

    /*
    Deze methode wordt aangeroepen in GUI.java
    Deze methode voegt een cursus toe aan de database via de gegevens die zijn ingevuld in de GUI
     */
    public static void addCursus(String courseName, String topic, String introText, String level) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(connectionUrl);
            String SQL = "INSERT INTO course (courseName, topic, introText, level) VALUES ('" + courseName + "', '" + topic + "', '" + introText + "', '" + level + "')";
            stmt = con.createStatement();
            stmt.executeUpdate(SQL);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            closeConnection(con, stmt);
            updateCursusArray();
        }
    }

    /*
    Deze methode wordt aangeroepen in GUI.java
    Deze methode verwijdert een cursus uit de database die is gekozen in de GUI
     */
    public static void deleteCursus(String courseName) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(connectionUrl);
            String SQL = "DELETE FROM Cursus WHERE courseName = '" + courseName + "'";
            stmt = con.createStatement();
            stmt.executeUpdate(SQL);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            closeConnection(con, stmt);
            updateCursusArray();
        }
    }

    /*
    Deze methode wordt aangeroepen in GUI.java
    Deze methode past een cursus aan in de database via de gegevens die zijn ingevuld in de GUI
     */
    public static void updateCursus(String oldCursusNaam, String courseName, String topic, String introText, String level) {
        try {

            cursusArray.clear();
            cursusNaamArray.clear();
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(connectionUrl);
            String SQL = "UPDATE Cursus SET courseName = '" + courseName + "', topic = '" + topic + "', introText = '" + introText + "', level = '" + level + "' WHERE courseName = '" + oldCursusNaam + "'";
            stmt = con.createStatement();
            stmt.executeUpdate(SQL);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            closeConnection(con, stmt);
            updateCursusArray();
        }
    }

    /*
    Deze methode wordt aangeroepen in DatabaseConnection.java
    Deze methode sluit de connectie met de database
     */
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


