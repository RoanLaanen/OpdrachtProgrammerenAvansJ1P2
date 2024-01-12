package com.codecademy.views;

import com.codecademy.Main;
import com.codecademy.controllers.UsersController;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;


public class UserCourses extends Application{

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Users");

        URL url = getClass().getResource("/UserCourses.fxml");


        Main.createStage(stage, url);
    }
    public static void main(String[] args) {
        launch(args);
    }
}