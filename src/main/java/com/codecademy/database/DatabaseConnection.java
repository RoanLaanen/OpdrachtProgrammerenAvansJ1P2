package com.codecademy.database;

import com.codecademy.models.Certificate;
import com.codecademy.models.Course;
import com.codecademy.models.User;
import com.codecademy.models.Level;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.*;

public class DatabaseConnection {
    private static final String connectionUrl = "jdbc:sqlserver://codecademy.database.windows.net:1433;database=codecademyData;user=groep5@codecademy;password=AvansBreda123;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30";
    private static Connection con = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;

    public static ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        try {
            con = DriverManager.getConnection(connectionUrl);
            String SQL = "SELECT * FROM [User]";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                users.add(new User(rs.getString("name"), rs.getString("email"), rs.getDate("dateOfBirth").toLocalDate(), rs.getString("gender"), rs.getString("address"), rs.getString("zip"), rs.getString("country")));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            if (rs != null) try {
                rs.close();
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
            closeConnection(con);
        }
        return users;
    }

    public static void addUser(User user) {
        try {
            con = DriverManager.getConnection(connectionUrl);
            String SQL = "INSERT INTO [User](name, email, dateOfBirth, gender, address, zip, country) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pst = con.prepareStatement(SQL)) {
                pst.setString(1, user.getName());
                pst.setString(2, user.getEmail().toLowerCase());
                pst.setString(3, user.getDateOfBirth().toString());
                pst.setString(4, user.getGender());
                pst.setString(5, user.getAddress());
                pst.setString(6, user.getZip());
                pst.setString(7, user.getCountry());
                pst.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            closeConnection(con);
        }
    }

    public static void updateUser(String selectedUser, User updatedUser) {
        try {
            con = DriverManager.getConnection(connectionUrl);
            String SQL = "UPDATE [User] SET name = ?, email = ?, dateOfBirth = ?, gender = ?, address = ?, zip = ?, country = ? WHERE email = ?";
            try (PreparedStatement pst = con.prepareStatement(SQL)) {
                pst.setString(1, updatedUser.getName());
                pst.setString(2, updatedUser.getEmail().toLowerCase());
                pst.setString(3, updatedUser.getDateOfBirth().toString());
                pst.setString(4, updatedUser.getGender());
                pst.setString(5, updatedUser.getAddress());
                pst.setString(6, updatedUser.getZip());
                pst.setString(7, updatedUser.getCountry());
                pst.setString(8, selectedUser);
                pst.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            closeConnection(con);
        }
    }

    public static void deleteUser(String selectedUser) {
        try {
            con = DriverManager.getConnection(connectionUrl);
            String SQL = "DELETE FROM [User] WHERE email = ?";
            try (PreparedStatement pst = con.prepareStatement(SQL)) {
                pst.setString(1, selectedUser);
                pst.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            closeConnection(con);
        }
    }

    public static ArrayList<Certificate> getCertificatesForUser(String selectedUser) {
        ArrayList<Certificate> certificates = new ArrayList<Certificate>();
        try {
        con = DriverManager.getConnection(connectionUrl);
        String SQL = "SELECT * FROM [Certificate] WHERE email = ?";
        try (PreparedStatement pst = con.prepareStatement(SQL)) {
            pst.setString(1, selectedUser);
            rs = pst.executeQuery();
            while (rs.next()) {
                certificates.add(new Certificate(rs.getInt("certificateID"), rs.getString("email"), rs.getString("courseName")));
            }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            closeConnection(con);
        }
        return certificates;
    }

    public static ArrayList<Course> getCoursesForUser(String selectedUser) {
        ArrayList<Course> courses = new ArrayList<Course>();
        try {
            con = DriverManager.getConnection(connectionUrl);
            String SQL = "SELECT * FROM [Course] WHERE courseName = (SELECT courseName FROM [Enrollment] WHERE email = ?)";
            try (PreparedStatement pst = con.prepareStatement(SQL)) {
                pst.setString(1, selectedUser);
                rs = pst.executeQuery();
                while (rs.next()) {
                    courses.add(new Course(rs.getString("courseName"), rs.getString("topic"), rs.getString("introText"), Level.valueOf(rs.getString("level"))));
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            closeConnection(con);
        }
        return courses;
    }

    private static void closeConnection(Connection con) {
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


