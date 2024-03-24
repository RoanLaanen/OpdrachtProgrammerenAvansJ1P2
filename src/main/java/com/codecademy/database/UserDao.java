package com.codecademy.database;

import com.codecademy.models.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import static com.codecademy.database.DatabaseConnection.userDatabaseChange;

public class UserDao {
    private static String connectionUrl = DatabaseConnectionManager.getConnectionUrl();
    private static SQLQueries sqlQueries = new SQLQueries();

    public UserDao(String connectionUrl) {
        UserDao.connectionUrl = connectionUrl;
        sqlQueries = new SQLQueries();
    }

    public static ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();

        try (Connection con = getConnection();
             Statement stmt = con.createStatement()) {

            String SQL = sqlQueries.getAllUsers();
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                users.add(new User(rs.getString("name"), rs.getString("email"), rs.getDate("dateOfBirth").toLocalDate(),
                        rs.getString("gender"), rs.getString("address"), rs.getString("zip"), rs.getString("country")));
            }
        } catch (Exception e) {
            outputError(e);
        }

        return users;
    }
    public static void addUser(User user) {
        performUpdate(sqlQueries.insertUser(), pst -> userDatabaseChange(user, pst));
    }
    public void enrollUserInCourse(String email, String selectedCourse) {
        performUpdate(sqlQueries.addUserToCourse(), pst -> {
            pst.setString(1, email);
            pst.setString(2, selectedCourse);
            pst.setString(3, LocalDate.now().toString());
        });
    }
    public void unenrollUserInCourse(String email, String selectedCourse) {
        performUpdate(sqlQueries.removeUserFromCourse(), pst -> {
            pst.setString(1, selectedCourse);
            pst.setString(2, email);
        });
    }
    public static void updateUser(String selectedUser, User updatedUser) {
        performUpdate(sqlQueries.updateUser(), pst -> {
            userDatabaseChange(updatedUser, pst);
            pst.setString(8, selectedUser);
        });
    }

    public static void deleteUser(String selectedUser) {
        performUpdate(sqlQueries.removeUser(), pst -> pst.setString(1, selectedUser));
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