package com.codecademy;


import com.codecademy.controllers.MainWindowController;
import com.codecademy.views.MainWindow;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Main {
    // Entry point of the application
    public static void main(String[] args) {
        MainWindow.main(args);
    }

    /**
     * Function to create a new JavaFX Stage.
     * A Stage in JavaFX is a window.
     * This method helps in code-reusability across various parts of the application where a new stage is required.
     *
     * @param stage - The primary Stage (window) of the application.
     * @param url - The URL of the FXML file that describes the UI layout of the stage.
     * @throws IOException if there are any issues loading the FXML file.
     */
    public static void createStage(Stage stage, URL url) throws IOException {
        // Instantiate a FXMLLoader with the given URL.
        FXMLLoader loader = new FXMLLoader(url);

        // Load the UI components from the FXML file using the loader
        Scene scene = new Scene(loader.load());

        // Make the stage not resizable
        stage.setResizable(false);

        // Get the Controller associated with the FXML file
        MainWindowController controller = loader.getController();

        // Set the scene for the stage (window)
        stage.setScene(scene);

        // Pass the control of the application's stage to the controller
        controller.setPrimaryStage(stage);

        // Display the Stage
        stage.show();
    }
}
