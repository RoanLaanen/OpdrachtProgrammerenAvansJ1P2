package com.codecademy.database;

import com.codecademy.models.Course;
import com.codecademy.models.Level;
import com.codecademy.models.User;

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

    public static ArrayList<User> getAllUsers() {

        ArrayList<User> users = new ArrayList<>();

        try {

            con = DriverManager.getConnection(connectionUrl);
            String SQL = "SELECT * FROM [User]";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                users.add(new User(rs.getString("name"), rs.getString("email"), rs.getString("dateOfBirth"), rs.getString("gender"), rs.getString("address"), rs.getString("zip"), rs.getString("country")));
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


