package com.codecademy.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CoursesController implements Initializable {
    @FXML
    private Stage primaryStage;

    @Override
    public void initialize(URL location, ResourceBundle resources) { }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

}