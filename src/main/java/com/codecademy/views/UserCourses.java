package com.codecademy.views;

import com.codecademy.Main;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;


public class UserCourses extends Application{

    // Start method override to initiate the application window
    @Override
    public void start(Stage stage) throws IOException {
        // Setting application window's title
        stage.setTitle("Roan Laanen (2197302), Dymo Waltheer (2215378), Stef Rensma (2217058)");

        // Fetching the URL for UserCourses.fxml
        URL url = getClass().getResource("/UserCourses.fxml");

        // Setup and display the stage using the fetched URL
        Main.createStage(stage, url);
    }

    // Main method that triggers the start of the JavaFX application
    public static void main(String[] args) {
        launch(args);
    }
}