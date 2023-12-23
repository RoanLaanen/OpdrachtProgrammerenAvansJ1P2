package com.example.verticalslice;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.io.File;
import java.util.Objects;

public class GUI extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Codecademy");

        URL url = getClass().getResource("/MainWindow.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        Scene scene = new Scene(loader.load());
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        MainWindowController controller = loader.getController();
        stage.setScene(scene);
        controller.setPrimaryStage(stage);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
