package com.codecademy.views;

import com.codecademy.controllers.MainWindowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Courses extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Courses");

        URL url = getClass().getResource("/Courses.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        Scene scene = new Scene(loader.load());
        stage.setResizable(false);

        MainWindowController controller = loader.getController();
        stage.setScene(scene);
        controller.setPrimaryStage(stage);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
