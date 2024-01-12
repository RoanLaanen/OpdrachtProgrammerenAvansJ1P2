package com.codecademy.views;

import com.codecademy.Main;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

// This is a JavaFX application class that represents the 'Courses' section of our application.
public class Courses extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // We're setting the title of our window (stage) here.
        stage.setTitle("Roan Laanen (2197302), Dymo Waltheer (2215378), Stef Rensma (2217058)");

        // Here we're getting a Resource by its name. This specific resource is an FXML file
        // which represents a specific layout of our application.
        URL url = getClass().getResource("/Courses.fxml");

        // 'Main.createStage(stage, url);' is a static method that encapsulates the whole process
        // of creating a JavaFX Scene, setting it to the stage, and then showing this stage.
        Main.createStage(stage, url);
    }

    // Entry point of 'Courses' application.
    public static void main(String[] args) {
        launch(args);
    }
}