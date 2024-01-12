package com.codecademy.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.net.URL;
import java.util.*;
import java.io.IOException;

// This is the main controller for managing different scenes/windows in the application.
public class MainWindowController implements Initializable {
    @FXML
    // Primary stage of the application
    protected Stage primaryStage;

    @Override
    // This is a controller initialization method
    public void initialize(URL location, ResourceBundle resources) { }

    // Method for setting the primary stage of the application
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    // Method to change the current scene to Courses
    public void changeSceneToCourses(ActionEvent event) throws IOException {
        // Loading FXML for Courses scene
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Courses.fxml")));
        Scene scene = new Scene(parent);
        // Getting the window that the event occurred in and changing the scene
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    // Method to change the current scene to Users
    public void changeSceneToUsers(ActionEvent event) throws IOException {
        // Loading FXML for Users scene
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Users.fxml")));
        Scene scene = new Scene(parent);
        // Getting the window that the event occurred in and changing the scene
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    // Method to change the current scene to Webcasts
    public void changeSceneToWebcasts(ActionEvent event) throws IOException {
        // Loading FXML for Webcasts scene
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Webcasts.fxml")));
        Scene scene = new Scene(parent);
        // Getting the window that the event occurred in and changing the scene
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}