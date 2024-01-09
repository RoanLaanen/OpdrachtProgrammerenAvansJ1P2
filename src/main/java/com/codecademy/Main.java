package com.codecademy;


import com.codecademy.controllers.MainWindowController;
import com.codecademy.database.DatabaseConnection;
import com.codecademy.views.MainWindow;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        DatabaseConnection.updateCursusArray();
        MainWindow.main(args);
    }

    public static void createStage(Stage stage, URL url) throws IOException {
        FXMLLoader loader = new FXMLLoader(url);
        Scene scene = new Scene(loader.load());
        stage.setResizable(false);

        MainWindowController controller = loader.getController();
        stage.setScene(scene);
        controller.setPrimaryStage(stage);
        stage.show();
    }
}
