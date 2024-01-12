package com.codecademy.controllers;

import com.codecademy.database.DatabaseConnection;
import com.codecademy.models.Course;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class CoursesController implements Initializable {
    public ListView<String> courseList;
    public ArrayList<Course> courses;
    @FXML
    private Stage primaryStage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        courses = DatabaseConnection.getAllCourses();




        courseList.getItems().addAll("Course 1", "Course 2", "Course 3");
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void changeSceneToMain(MouseEvent mouseEvent) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/MainWindow.fxml")));
        Scene scene = new Scene(parent);

        Stage window = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }
}