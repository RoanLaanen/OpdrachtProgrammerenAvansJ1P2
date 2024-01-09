package com.codecademy.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.*;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;



public class MainWindowController implements Initializable {
    @FXML
    private Stage primaryStage;

    @FXML private ComboBox<String> course_combobox;
    @FXML private Button addButton, editButton, deleteButton;
    @FXML private Label title_label, topic_label, level_label;
    @FXML private Text introText;

    private int selectedIndex = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) { }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void changeSceneToCourses(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/Courses.fxml"));
        Scene scene = new Scene(parent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}