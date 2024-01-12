package com.codecademy.views;

import com.codecademy.Main;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * This is the main window of the application and the entry point of the JavaFX application.
 */
public class MainWindow extends Application {
    @Override
    // This method is the initial point for any JavaFX application. It takes in the primary stage.
    public void start(Stage stage) throws IOException {
        // Setting the title of the application window.
        stage.setTitle("Roan Laanen (2197302), Dymo Waltheer (2215378), Stef Rensma (2217058)");

        // Retrieving the URL of the MainWindow.fxml which houses the layout and UI of the main window.
        URL url = getClass().getResource("/MainWindow.fxml");

        // Creating stage with the given fxml url in the main stage.
        Main.createStage(stage, url);
    }

    // The main method that gets called to start the JavaFX application.
    public static void main(String[] args) {
        launch(args);
    }
}
