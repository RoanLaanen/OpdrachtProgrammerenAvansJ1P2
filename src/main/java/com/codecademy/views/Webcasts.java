package com.codecademy.views;

import com.codecademy.Main;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;


public class Webcasts extends Application{

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Roan Laanen (2197302), Dymo Waltheer (2215378), Stef Rensma (2217058)");

        URL url = getClass().getResource("/Webcasts.fxml");
        Main.createStage(stage, url);
    }
    public static void main(String[] args) {
        launch(args);
    }
}
