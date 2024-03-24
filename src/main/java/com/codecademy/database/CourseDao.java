package com.codecademy.database;

import com.codecademy.models.Course;
import com.codecademy.models.Level;

import java.sql.*;
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

    public static void addCourse(Course course) {
        performUpdate(sqlQueries.addCourse(), pst -> courseDatabaseChange(course, pst));
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