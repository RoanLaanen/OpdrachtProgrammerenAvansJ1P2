package com.codecademy.database;

import com.codecademy.models.*;
import com.codecademy.models.Module;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class DatabaseConnection {
    private static final String connectionUrl = "jdbc:sqlserver://codecademy.database.windows.net:1433;database=codecademyData;user=groep5@codecademy;password=AvansBreda123;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30";
    private static final Connection con = DatabaseConnectionManager.getCon();
    private static Statement stmt = null;
    private static ResultSet rs = null;
    static SQLQueries sqlQueries = new SQLQueries();

    public static ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        try {
            String SQL = sqlQueries.getAllUsers();
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
            closeConnection();
        }
        return users;
    }

    public static ArrayList<Course> getAllCourses() {
        ArrayList<Course> courses = new ArrayList<>();
        try {
            String SQL = sqlQueries.getAllCourses();
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
            closeConnection();
        }
        return courses;
    }

    public static ArrayList<Course> getAllCoursesWhereNotEnrolled(String email) {
        ArrayList<Course> courses = new ArrayList<>();
        try {
            String SQL = sqlQueries.getAllCoursesWhereNotEnrolled();
            try (PreparedStatement pst = con.prepareStatement(SQL)) {
                pst.setString(1, email);
                rs = pst.executeQuery();
                while (rs.next()) {
                    courses.add(new Course(rs.getString("courseName"), rs.getString("topic"), rs.getString("introText"), Level.valueOf(rs.getString("level"))));
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            closeConnection();
        }
        return courses;
    }


    public static void addUser(User user) {
        try {
            String SQL = sqlQueries.insertUser();
            try (PreparedStatement pst = con.prepareStatement(SQL)) {
                userDatabaseChange(user, pst);
                pst.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            closeConnection();
        }
    }

    public static void enrollUserInCourse(String email, String selectedCourse) {
        try {
            String SQL = sqlQueries.addUserToCourse();
            try (PreparedStatement pst = con.prepareStatement(SQL)) {
                pst.setString(1, email);
                pst.setString(2, selectedCourse);
                pst.setString(3, LocalDate.now().toString());
                pst.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            closeConnection();
        }
    }

    public static void unenrollUserInCourse(String email, String selectedCourse) {
        try {
            String SQL = sqlQueries.removeUserFromCourse();
            try (PreparedStatement pst = con.prepareStatement(SQL)) {
                pst.setString(1, selectedCourse);
                pst.setString(2, email);
                pst.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            closeConnection();
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
            String SQL = sqlQueries.updateUser();
            try (PreparedStatement pst = con.prepareStatement(SQL)) {
                userDatabaseChange(updatedUser, pst);
                pst.setString(8, selectedUser);
                pst.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            closeConnection();
        }
    }

    public static void deleteUser(String selectedUser) {
        try {
            String SQL = sqlQueries.removeUser();
            try (PreparedStatement pst = con.prepareStatement(SQL)) {
                pst.setString(1, selectedUser);
                pst.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            closeConnection();
        }
    }

    public static ArrayList<Certificate> getCertificatesForUser(String selectedUser) {
        ArrayList<Certificate> certificates = new ArrayList<>();
        try {
            
            String SQL = sqlQueries.getCertificatesForUser();
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
            closeConnection();
        }
        return certificates;
    }

    public static ArrayList<Course> getCoursesForUser(String selectedUser) {
        ArrayList<Course> courses = new ArrayList<>();
        try {
            
            String SQL = sqlQueries.getCoursesForUser();
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
            closeConnection();
        }
        return courses;
    }

    public static void addCourse(Course course) {
        try {
            
            String SQL = sqlQueries.addCourse();
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
            closeConnection();
        }
    }

    public static void updateCourse(String selectedCourse, Course course) {
        try {
            
            String SQL = sqlQueries.updateCourse();
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
            closeConnection();
        }
    }

    public static void deleteCourse(String selectedCourse) {
        try {
            
            String SQL = sqlQueries.deleteCourse();
            try (PreparedStatement pst = con.prepareStatement(SQL)) {
                pst.setString(1, selectedCourse);
                pst.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            closeConnection();
        }
    }

    public static ArrayList<Module> getModulesForCourse(String selectedCourse) {
        ArrayList<Module> modules = new ArrayList<>();
        try {
            
            String SQL = sqlQueries.getModulesForCourse();
            try (PreparedStatement pst = con.prepareStatement(SQL)) {
                pst.setString(1, selectedCourse);
                rs = pst.executeQuery();
                while (rs.next()) {
                    Module module = new Module(Status.valueOf(rs.getString("status")), LocalDate.parse(rs.getString("publishingDate")), rs.getString("title"), rs.getFloat("version"), rs.getString("description"), rs.getString("contactEmail"), rs.getString("contactName"), rs.getInt("contentId"), rs.getString("courseName"));
                    modules.add(module);
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            closeConnection();
        }
        return modules;

    }
    public static ArrayList<ArrayList<String>> getAvailableModules() {
        ArrayList<String> availableModules = new ArrayList<>();
        ArrayList<String> availableModuleIds = new ArrayList<>();
        try {
            
            String SQL = sqlQueries.getAvailableModules();
            try (PreparedStatement pst = con.prepareStatement(SQL)) {
                rs = pst.executeQuery();
                while (rs.next()) {
                    availableModules.add(rs.getString("title"));
                    int id = rs.getInt("contentId");
                    availableModuleIds.add(Integer.toString(id));
                }
            }
        } catch (Exception e) {
            System.out.println("Erroreee: " + e);
        } finally {
            closeConnection();
        }

        ArrayList<ArrayList<String>> doeble = new ArrayList<>();
        doeble.add(availableModules);
        doeble.add(availableModuleIds);

        return doeble;
    }

    public static void addModuleToCourse(String courseName, String contentId) {
        try {
            
            String SQL = sqlQueries.addModuleToCourse();
            try (PreparedStatement pst = con.prepareStatement(SQL)) {
                pst.setString(1, courseName);
                pst.setString(2, contentId);
                pst.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            closeConnection();
        }
    }


    public static float getAvgCompletionRateModule(int contentId) {
        float avgCompletionRate = 0.0F;
        try {
            
            String SQL = sqlQueries.getAvgCompletionRateModule();
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
            closeConnection();
        }
        return avgCompletionRate;
    }

    private static void closeConnection() {
        if (stmt != null) try {
            stmt.close();
        } catch (Exception exception) {
            System.out.println("Error: " + exception);
        }
        if (DatabaseConnection.con != null) try {
            DatabaseConnection.con.close();
        } catch (Exception exception) {
            System.out.println("Error: " + exception);
        }
    }

    public static int getAmountCompleted(String coursename) {
        int total = 0;
        try {
            
            String SQL = sqlQueries.getAmountCompleted();
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
            closeConnection();
        }
        return 0;
    }

    public static float getMaleCompletionRateForCourse(String selectedCourse) {
        int amountCompleted = 0;
        int amountTotal = 0;
        try {
            
            String SQL = sqlQueries.getMaleCompletionRate();
            try (PreparedStatement pst = con.prepareStatement(SQL)) {
                pst.setString(1, selectedCourse);
                rs = pst.executeQuery();
                while (rs.next()) {
                    amountCompleted = rs.getInt("amountCompleted");
                }
            }
            SQL = sqlQueries.getMaleCompletionAmount();
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
            closeConnection();
        }
        return 0;
    }

    public static float getFemaleCompletionRateForCourse(String selectedCourse) {
        int amountCompleted = 0;
        int amountTotal = 0;
        try {
            
            String SQL = sqlQueries.getFemaleCompletionRate();
            try (PreparedStatement pst = con.prepareStatement(SQL)) {
                pst.setString(1, selectedCourse);
                rs = pst.executeQuery();
                while (rs.next()) {
                    amountCompleted = rs.getInt("amountCompleted");
                }
            }
            SQL = sqlQueries.getFemaleCompletionAmount();
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
            closeConnection();
        }
        return 0;
    }

    public static ArrayList<Webcast> getWebcasts() {
        ArrayList<Webcast> webcasts = new ArrayList<>();
        try {
            
            String SQL = sqlQueries.getAllWebcasts();
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
            closeConnection();
        }
        return webcasts;
    }

    public static ArrayList<Webcast> getTopWebcasts() {
        ArrayList<Webcast> webcasts = new ArrayList<>();
        try {
            
            String SQL = sqlQueries.getTopWebcasts();
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
            closeConnection();
        }
        return webcasts;
    }

    public static Course getCourseFromName(String selectedCourse) {
        Course courseObject = null;
        try {
            
            String SQL = sqlQueries.getCourseFromName();

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
            closeConnection();
        }
        return courseObject;
    }


}


