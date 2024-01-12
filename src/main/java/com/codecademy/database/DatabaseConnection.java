package com.codecademy.database;

import com.codecademy.models.*;
import com.codecademy.models.Module;

import java.sql.*;
import java.time.LocalDate;
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

    public static ArrayList<Course> getAllCourses() {
        ArrayList<Course> courses = new ArrayList<>();
        try {
            con = DriverManager.getConnection(connectionUrl);
            String SQL = "SELECT * FROM [Course]";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                courses.add(new Course(rs.getString("courseName"), rs.getString("topic"), rs.getString("introText"), Level.valueOf(rs.getString("level"))));
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
        return courses;
    }


    public static void addUser(User user) {
        try {
            con = DriverManager.getConnection(connectionUrl);
            String SQL = "INSERT INTO [User](name, email, dateOfBirth, gender, address, zip, country) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pst = con.prepareStatement(SQL)) {
                userDatabaseChange(user, pst);
                pst.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            closeConnection(con);
        }
    }

    public static void userDatabaseChange(User user, PreparedStatement pst) throws SQLException {
        pst.setString(1, user.getName());
        pst.setString(2, user.getEmail().toLowerCase());
        pst.setString(3, user.getDateOfBirth().toString());
        pst.setString(4, user.getGender());
        pst.setString(5, user.getAddress());
        pst.setString(6, user.getZip());
        pst.setString(7, user.getCountry());
    }

    public static void updateUser(String selectedUser, User updatedUser) {
        try {
            con = DriverManager.getConnection(connectionUrl);
            String SQL = "UPDATE [User] SET name = ?, email = ?, dateOfBirth = ?, gender = ?, address = ?, zip = ?, country = ? WHERE email = ?";
            try (PreparedStatement pst = con.prepareStatement(SQL)) {
                userDatabaseChange(updatedUser, pst);
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
        ArrayList<Certificate> certificates = new ArrayList<>();
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
        ArrayList<Course> courses = new ArrayList<>();
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

    public static void addCourse(Course course) {
        try {
            con = DriverManager.getConnection(connectionUrl);
            String SQL = "INSERT INTO [Course](courseName, introText, topic, level) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pst = con.prepareStatement(SQL)) {
                pst.setString(1, course.getCourseName());
                pst.setString(2, course.getIntroText());
                pst.setString(3, course.getTopic());
                pst.setString(4, course.getLevel());
                pst.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            closeConnection(con);
        }
    }

    public static void updateCourse(String selectedCourse, Course course) {
        try {
            con = DriverManager.getConnection(connectionUrl);
            String SQL = "UPDATE [Course] SET courseName = ?, introText = ?, topic = ?, level = ? WHERE courseName = ?";
            try (PreparedStatement pst = con.prepareStatement(SQL)) {
                pst.setString(1, course.getCourseName());
                pst.setString(2, course.getIntroText());
                pst.setString(3, course.getTopic());
                pst.setString(4, course.getLevel());
                pst.setString(5, selectedCourse);
                pst.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            closeConnection(con);
        }
    }

    public static void deleteCourse(String selectedCourse) {
        try {
            con = DriverManager.getConnection(connectionUrl);
            String SQL = "DELETE FROM [Course] WHERE courseName = ?";
            try (PreparedStatement pst = con.prepareStatement(SQL)) {
                pst.setString(1, selectedCourse);
                pst.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            closeConnection(con);
        }
    }

    public static ArrayList<Module> getModulesForCourse(String selectedCourse) {
        ArrayList<Module> modules = new ArrayList<>();
        try {
            con = DriverManager.getConnection(connectionUrl);
            String SQL = "SELECT * FROM [Module] LEFT JOIN [ContentItem] ON [Module].contentId = [ContentItem].contentId WHERE courseName = ?";
            try (PreparedStatement pst = con.prepareStatement(SQL)) {
                pst.setString(1, selectedCourse);
                rs = pst.executeQuery();
                while (rs.next()) {
                    modules.add(new Module(Status.valueOf(rs.getString("status")), LocalDate.parse(rs.getString("publishingDate")), rs.getString("title"), rs.getFloat("version"), rs.getString("description"), rs.getString("contactEmail"), rs.getString("contactName"), rs.getInt("contentId"), rs.getString("courseName")));
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            closeConnection(con);
        }
        return modules;
    }
    public static ArrayList<ArrayList<String>> getAvailableModules() {
        ArrayList<String> availableModules = new ArrayList<>();
        ArrayList<String> availableModuleIds = new ArrayList<>();
        try {
            con = DriverManager.getConnection(connectionUrl);
            String SQL = "SELECT * FROM [Module] LEFT JOIN [ContentItem] ON [Module].contentId = [ContentItem].contentId WHERE courseName IS NULL";
            try (PreparedStatement pst = con.prepareStatement(SQL)) {
                rs = pst.executeQuery();
                while (rs.next()) {
                    availableModules.add(rs.getString("title"));
                    availableModuleIds.add(rs.getString("contentId"));
                }
            }
        } catch (Exception e) {
            System.out.println("Erroreee: " + e);
        } finally {
            closeConnection(con);
        }

        ArrayList<ArrayList<String>> doeble = new ArrayList<>();
        doeble.add(availableModules);
        doeble.add(availableModuleIds);

        return doeble;
    };

    public static void addModuleToCourse(String courseName, String contentId) {
        try {
            con = DriverManager.getConnection(connectionUrl);
            String SQL = "UPDATE [Module] SET courseName = ? WHERE contentId = ?";
            try (PreparedStatement pst = con.prepareStatement(SQL)) {
                pst.setString(1, courseName);
                pst.setString(2, contentId);
                pst.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            closeConnection(con);
        }
    }




    public static float getAvgCompletionRateModule(int contentId) {
        float avgCompletionRate = 0.0F;
        try {
            con = DriverManager.getConnection(connectionUrl);
            String SQL = "SELECT AVG(viewedPercentage) as avgCompletionRate FROM [Viewed] WHERE contentId = ?";
            try (PreparedStatement pst = con.prepareStatement(SQL)) {
                pst.setInt(1, contentId);
                rs = pst.executeQuery();
                while (rs.next()) {
                    avgCompletionRate = rs.getFloat("avgCompletionRate");
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            closeConnection(con);
        }
        return avgCompletionRate;
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

    public static <Int> int getAmountCompleted(String coursename) {
        int total = 0;
        try {
            con = DriverManager.getConnection(connectionUrl);
            String SQL = "SELECT courseName, count(*) AS totalCertificates FROM Certificate WHERE courseName = ? group by courseName";
            try (PreparedStatement pst = con.prepareStatement(SQL)) {
                pst.setString(1, coursename);
                rs = pst.executeQuery();
                while (rs.next()) {
                    total = Integer.parseInt(rs.getString("totalCertificates"));
                }
                return total;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            closeConnection(con);
        }
        return 0;
    }

    public static float getMaleCompletionRateForCourse(String selectedCourse) {
        int amountCompleted = 0;
        int amountTotal = 0;
        try {
            con = DriverManager.getConnection(connectionUrl);
            String SQL = "SELECT COUNT(*) AS amountCompleted FROM [Certificate] WHERE courseName = ? AND email IN (SELECT email FROM [User] WHERE gender = 'Male')";
            try (PreparedStatement pst = con.prepareStatement(SQL)) {
                pst.setString(1, selectedCourse);
                rs = pst.executeQuery();
                while (rs.next()) {
                    amountCompleted = rs.getInt("amountCompleted");
                }
            }
            SQL = "SELECT COUNT(*) AS amountTotal FROM [Enrollment] WHERE email IN (SELECT email FROM [User] WHERE gender = 'Male')";
            try (PreparedStatement pst = con.prepareStatement(SQL)) {
                rs = pst.executeQuery();
                while (rs.next()) {
                    amountTotal = rs.getInt("amountTotal");
                }
            }
            if (amountTotal == 0) {
                return 0.0F;
            } else {
                return (float) amountCompleted / amountTotal * 100;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            closeConnection(con);
        }
        return 0;
    }

    public static float getFemaleCompletionRateForCourse(String selectedCourse) {
        int amountCompleted = 0;
        int amountTotal = 0;
        try {
            con = DriverManager.getConnection(connectionUrl);
            String SQL = "SELECT COUNT(*) AS amountCompleted FROM [Certificate] WHERE courseName = ? AND email IN (SELECT email FROM [User] WHERE gender = 'Female')";
            try (PreparedStatement pst = con.prepareStatement(SQL)) {
                pst.setString(1, selectedCourse);
                rs = pst.executeQuery();
                while (rs.next()) {
                    amountCompleted = rs.getInt("amountCompleted");
                }
            }
            SQL = "SELECT COUNT(*) AS amountTotal FROM [Enrollment] WHERE email IN (SELECT email FROM [User] WHERE gender = 'Female')";
            try (PreparedStatement pst = con.prepareStatement(SQL)) {
                rs = pst.executeQuery();
                while (rs.next()) {
                    amountTotal = rs.getInt("amountTotal");
                }
            }
            if (amountTotal == 0) {
                return 0.0F;
            } else {
                return (float) amountCompleted / amountTotal * 100;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            closeConnection(con);
        }
        return 0;
    }

    public static ArrayList<Webcast> getWebcasts() {
        ArrayList<Webcast> webcasts = new ArrayList<>();
        try {
            con = DriverManager.getConnection(connectionUrl);
            String SQL = "SELECT * FROM [Webcast] LEFT JOIN [ContentItem] ON [Webcast].contentId = [ContentItem].contentId";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                webcasts.add(new Webcast(Status.valueOf(rs.getString("status")), LocalDate.parse(rs.getString("publishingDate")), rs.getString("title"), rs.getInt("duration"), rs.getString("description"), rs.getString("nameSpeaker"), rs.getString("orgSpeaker"), rs.getInt("contentId"), rs.getString("url")));
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
        return webcasts;
    }

    public static ArrayList<Webcast> getTopWebcasts() {
        ArrayList<Webcast> webcasts = new ArrayList<>();
        try {
            con = DriverManager.getConnection(connectionUrl);
            String SQL = "SELECT * FROM [Webcast] LEFT JOIN [ContentItem] ON [Webcast].contentId = [ContentItem].contentId WHERE Webcast.contentId IN (SELECT TOP(3) contentId FROM [Viewed] GROUP BY contentId ORDER BY COUNT(contentId) DESC)";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                webcasts.add(new Webcast(Status.valueOf(rs.getString("status")), LocalDate.parse(rs.getString("publishingDate")), rs.getString("title"), rs.getInt("duration"), rs.getString("description"), rs.getString("nameSpeaker"), rs.getString("orgSpeaker"), rs.getInt("contentId"), rs.getString("url")));
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
        return webcasts;
    }

    public static Course getCourseFromName(String selectedCourse) {
        Course courseObject = null;
        try {
            con = DriverManager.getConnection(connectionUrl);
            String SQL = "SELECT TOP(1) * FROM [Course] WHERE courseName = ?";
            try (PreparedStatement pst = con.prepareStatement(SQL)) {
                pst.setString(1, selectedCourse);
                rs = pst.executeQuery();
                while (rs.next()) {
                    courseObject = new Course(rs.getString("courseName"), rs.getString("topic"), rs.getString("introText"), Level.valueOf(rs.getString("level")));
                }

            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            closeConnection(con);
        }
        return courseObject;
    }


}


