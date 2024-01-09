package com.codecademy.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.*;

import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainWindowController implements Initializable {
    @FXML
    private BorderPane border_pane;
    private Stage primaryStage;

    @FXML private ComboBox<String> course_combobox;
    @FXML private Button addButton, editButton, deleteButton;
    @FXML private Label title_label, topic_label, level_label;
    @FXML private Text introText;

    private int selectedIndex = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }



}