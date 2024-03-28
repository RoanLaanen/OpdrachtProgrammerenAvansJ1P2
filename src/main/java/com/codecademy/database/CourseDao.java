package com.codecademy.database;

import com.codecademy.models.Course;
import com.codecademy.models.Level;
import com.codecademy.models.Module;
import com.codecademy.models.Status;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import static com.codecademy.database.DatabaseConnection.courseDatabaseChange;

public class CourseDao {
    private static String connectionUrl = DatabaseConnectionManager.getConnectionUrl();
    private static SQLQueries sqlQueries = new SQLQueries();

    public CourseDao(String connectionUrl) {
        CourseDao.connectionUrl = connectionUrl;
        sqlQueries = new SQLQueries();
    }

    public static ArrayList<Course> getAllCourses() {
        ArrayList<Course> courses = new ArrayList<>();

        try (Connection con = getConnection();
             Statement stmt = con.createStatement()) {

            String SQL = sqlQueries.getAllCourses();
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                courses.add(new Course(rs.getString("courseName"), rs.getString("introText"), rs.getString("topic"),
                        Level.valueOf(rs.getString("level"))));
            }
        } catch (Exception e) {
            outputError(e);
        }

        return courses;
    }

    public static ArrayList<Module> getModulesForCourse(String selectedCourse) {
        ArrayList<Module> modules = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(sqlQueries.getModulesForCourse())) {

            pst.setString(1, selectedCourse);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Module module = new Module(Status.valueOf(rs.getString("status")), LocalDate.parse(rs.getString("publishingDate")), rs.getString("title"), rs.getFloat("version"), rs.getString("description"), rs.getString("contactEmail"), rs.getString("contactName"), rs.getInt("contentId"), rs.getString("courseName"));
                modules.add(module);
            }
        } catch (Exception e) {
            outputError(e);
        }
        return modules;
    }



    public static void addCourse(Course course) {
        performUpdate(sqlQueries.addCourse(), pst -> courseDatabaseChange(course, pst));
    }

    public static void deleteCourse(String selectedCourse) {
        performUpdate(sqlQueries.deleteCourse(), pst -> pst.setString(1, selectedCourse));
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(connectionUrl);
    }




    private static void performUpdate(String SQL, ThrowingConsumer<PreparedStatement> consumer) {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(SQL)) {

            consumer.accept(pst);
            pst.executeUpdate();
        } catch (Exception e) {
            outputError(e);
        }
    }

    private static void outputError(Exception e) {
        System.out.println("Error: " + e);
    }

    @FunctionalInterface
    interface ThrowingConsumer<T> {
        void accept(T t) throws Exception;
    }
}