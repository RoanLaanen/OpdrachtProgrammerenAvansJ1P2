package com.codecademy.controllers;

import com.codecademy.database.DatabaseConnection;
import com.codecademy.models.Course;
import com.codecademy.models.Level;
import com.codecademy.models.Module;
import com.codecademy.models.User;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.Bidi;
import java.util.*;

public class CoursesController implements Initializable {
    public TextArea introTextArea;
    public ComboBox<Level> levelBox;
    public Label completionText;

    public Label completionTextMale;

    public Label completionTextFemale;
    String selectedCourse;
    public ListView<String> courseList;
    public TextField nameField;
    public TextField topicField;
    public ListView<String> modulesList;

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
        levelBox.getItems().setAll(Level.Beginner, Level.Intermediate, Level.Expert, Level.None);
        courseList.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            selectedCourse = courseList.getSelectionModel().getSelectedItem();
            float maleCompletionRate = DatabaseConnection.getMaleCompletionRateForCourse(selectedCourse);
            float femaleCompletionRate = DatabaseConnection.getFemaleCompletionRateForCourse(selectedCourse);
            int totalCOmpletions = DatabaseConnection.getAmountCompleted(selectedCourse);
            completionText.textProperty().bind(Bindings.concat("User completion count: ", totalCOmpletions+""));
            completionTextMale.textProperty().bind(Bindings.concat("Male completion rate: ", maleCompletionRate+"%"));
            completionTextFemale.textProperty().bind(Bindings.concat("Female completion rate: ", femaleCompletionRate+"%"));
            nameField.setText(selectedCourse);
            introTextArea.setText(introTexts.get(selectedCourse));
            topicField.setText(topics.get(selectedCourse));
            levelBox.getSelectionModel().select(levels.get(selectedCourse));
            ArrayList<Module> modules = new ArrayList<Module>();
            modules = DatabaseConnection.getModulesForCourse(courseList.getSelectionModel().getSelectedItem());
            ArrayList<String> moduleNames = new ArrayList<String>();

            for (Module module : modules) {
                moduleNames.add(module.getTitle() + "                                   Average Completion: " + DatabaseConnection.getAvgCompletionRateModule(module.getContentID()) + "%");
            }



            ArrayList<String> listOfModuleValues = new ArrayList<>(moduleNames);
            ObservableList<String> items = FXCollections.observableArrayList((listOfModuleValues));
            modulesList.setItems(items);
        });
    }

    public void changeSceneToMain(MouseEvent mouseEvent) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/MainWindow.fxml")));
        Scene scene = new Scene(parent);

        Stage window = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void saveCourse() {
        Course newCourse = new Course(nameField.getText(), topicField.getText(), introTextArea.getText(), levelBox.getValue());
        if(selectedCourse == null) {
            DatabaseConnection.addCourse(newCourse);
        }
        else {
            DatabaseConnection.updateCourse(selectedCourse, newCourse);
        }
        extractCourseData();
        selectedCourse = newCourse.getCourseName();
        courseList.getSelectionModel().select(selectedCourse);
    }

    public void addCourse() {
        nameField.clear();
        topicField.clear();
        introTextArea.clear();
        levelBox.getSelectionModel().clearSelection();
        nameField.setPromptText("Course Name");
        topicField.setPromptText("Course Topic");
        introTextArea.setPromptText("Course Intro Text");
        levelBox.setPromptText("Level");
        selectedCourse = null;
    }

    public void deleteCourse() {
        if (selectedCourse != null) {
            DatabaseConnection.deleteCourse(selectedCourse);
            extractCourseData();
            selectedCourse = null;
        }
    }
}