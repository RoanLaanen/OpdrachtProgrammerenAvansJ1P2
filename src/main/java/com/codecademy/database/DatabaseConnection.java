package com.codecademy.database;

import com.codecademy.models.Course;
import com.codecademy.models.Level;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseConnection {
    private static ArrayList<Course> cursusArray = new ArrayList<>();
    private static ArrayList<String> cursusNaamArray = new ArrayList<>();
    private static final String connectionUrl = "jdbc:sqlserver://codecademy.database.windows.net:1433;database=codecademyData;user=groep5@codecademy;password=AvansBreda123;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30";
    private static Connection con = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;

    public static void updateCursusArray() {

        try {

            con = DriverManager.getConnection(connectionUrl);
            String SQL = "SELECT * FROM [User]";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                System.out.println(rs.getString("email"));
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

    public static ArrayList<ArrayList<String>> getAllUsers() {

        ArrayList<ArrayList<String>> users = new ArrayList<>();

        try {

            con = DriverManager.getConnection(connectionUrl);
            String SQL = "SELECT * FROM [User]";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                ArrayList<String> row = new ArrayList<>();
                row.add(rs.getString("name"));
                row.add(rs.getString("email"));
                row.add(rs.getString("dateOfBirth"));
                row.add(rs.getString("gender"));
                row.add(rs.getString("address"));
                row.add(rs.getString("zip"));
                row.add(rs.getString("country"));
                users.add(row);
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
        return users;
    }

    public static void deleteUser(String userEmail) {
        try {
            con = DriverManager.getConnection(connectionUrl);
            String SQL = "DELETE FROM [User] WHERE email = '" + userEmail + "'";
            stmt = con.createStatement();
            stmt.executeUpdate(SQL);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            closeConnection(con, stmt);
            updateCursusArray();
        }
    }





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


