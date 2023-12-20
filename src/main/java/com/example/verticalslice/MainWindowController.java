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

public class MainWindowController implements Initializable {
    @FXML
    private BorderPane border_pane;

    @FXML private ComboBox<String> course_combobox;
    @FXML private Button addButton, editButton, deleteButton;
    @FXML private Label title_label, topic_label, level_label;
    @FXML private Text introText;

    private int selectedIndex = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        course_combobox.setItems(FXCollections.observableArrayList(DatabaseConnection.cursusNaamArray));
        course_combobox.getSelectionModel().selectFirst();
        setLabelsInfo();
        course_combobox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                selectedIndex = DatabaseConnection.cursusNaamArray.indexOf(t1);
                if (DatabaseConnection.cursusNaamArray.size() == 1) {
                    selectedIndex = 0;
                }
                setLabelsInfo();
            }
        });
    }


    private void setLabelsInfo() {
        title_label.setText(DatabaseConnection.cursusArray.get(selectedIndex).getCourseName());
        topic_label.setText(DatabaseConnection.cursusArray.get(selectedIndex).getTopic());
        String levelString = DatabaseConnection.cursusArray.get(selectedIndex).getLevel();
        if (levelString.equals("niks")) {
            levelString = "";
        }
        level_label.setText(levelString);
        introText.setText(DatabaseConnection.cursusArray.get(selectedIndex).getIntroText());
    }

    public void addButtonAction(){
        //add code here
    }

    public void deleteButtonAction(){
        //add delete button code here
    }
}