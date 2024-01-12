package com.codecademy.controllers;

import com.codecademy.database.DatabaseConnection;
import com.codecademy.models.Course;
import com.codecademy.models.Level;
import com.codecademy.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class CoursesController implements Initializable {
    public ListView<String> courseList;
    public TextField nameField;
    public TextField topicField;
    public ListView<String> modulesList;
    public ListView<String>  webcastsList;
    public ListView<String>  mostViewed;

    HashMap<String, String> courseNames = new HashMap<>();
    HashMap<String, String> topics = new HashMap<>();
    HashMap<String, String> introTexts = new HashMap<>();
    HashMap<String, Level> levels = new HashMap<>();

    private void extractCourseData(){
        ArrayList<Course> courses = DatabaseConnection.getAllCourses();
        courseNames.clear();
        topics.clear();
        introTexts.clear();
        levels.clear();
        for (Course course : courses) {
            String courseName = course.getCourseName();
            courseNames.put(courseName, course.getCourseName());
            topics.put(courseName, course.getTopic());
            introTexts.put(courseName, course.getIntroText());
            levels.put(courseName, Level.valueOf(course.getLevel()));
        }
        Collection<String> values = courseNames.values();

        //Creating an ArrayList of values
        ArrayList<String> listOfCourseValues = new ArrayList<>(values);
        ObservableList<String> items = FXCollections.observableArrayList((listOfCourseValues));
        courseList.setItems(items);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        extractCourseData();
    }

    public void changeSceneToMain(MouseEvent mouseEvent) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/MainWindow.fxml")));
        Scene scene = new Scene(parent);

        Stage window = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}