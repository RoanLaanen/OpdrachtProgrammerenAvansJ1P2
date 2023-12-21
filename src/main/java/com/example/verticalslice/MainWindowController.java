package com.example.verticalslice;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.collections.FXCollections;
import javafx.beans.value.*;
import java.net.URL;
import java.util.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainWindowController implements Initializable {
    @FXML
    private BorderPane border_pane;

    // Define the Stage object
    private Stage primaryStage;

    @FXML private ComboBox<String> course_combobox;
    @FXML private Button addButton, editButton, deleteButton;
    @FXML private Label title_label, topic_label, level_label;
    @FXML private Text introText;

    private int selectedIndex = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    // Setter method to receive the stage from GUI.java
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void closeAppButton(){
        System.exit(0);
    }

    public void minimizeAppButton() {
        primaryStage.setIconified(true);
    }


}