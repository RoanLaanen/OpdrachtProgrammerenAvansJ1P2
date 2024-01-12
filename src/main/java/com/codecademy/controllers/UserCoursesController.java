package com.codecademy.controllers;

import com.codecademy.models.DataSingleton;
import com.codecademy.database.DatabaseConnection;
import com.codecademy.models.Course;

import com.codecademy.models.Module;
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
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class UserCoursesController implements Initializable {
    public ListView<String> modulesList;  // List view for modules
    String selectedUser;  // Selected user info
    String selectedCourse;  // Selected course info
    DataSingleton data = DataSingleton.getInstance();  // Instance of DataSingleton
    public Label courseNameUser;  // Label for user's course name
    public Label introTextUser;  // Label for introduction text
    public Label topicUser;  // Label for course topic
    public Label levelUser;  // Label for course level
    public ComboBox<String> courseListSelection;  // Combo box for course selection

    // Initialize user courses controller
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Retrieving selected user and course
        selectedUser = data.getSelectedUser();
        selectedCourse = data.getSelectedCourse();

        // Fetch database for matching courses and set course information
        Course selectedCoursesObject = DatabaseConnection.getCourseFromName(selectedCourse);
        courseNameUser.setText(selectedCoursesObject.getCourseName());
        introTextUser.setText(selectedCoursesObject.getIntroText());
        topicUser.setText(selectedCoursesObject.getTopic());
        levelUser.setText(selectedCoursesObject.getLevel());

        // Populate course list selection with courses where user is not enrolled
        ArrayList<Course> courses = DatabaseConnection.getAllCoursesWhereNotEnrolled(selectedUser);
        for (Course course : courses) {
            courseListSelection.getItems().add(course.getCourseName());
        }

        // Get the modules for the selected course and add them to the list
        ArrayList<Module> modules = DatabaseConnection.getModulesForCourse(selectedCourse);
        ArrayList<String> moduleNames = new ArrayList<>();
        for (Module module : modules){
            moduleNames.add(module.getTitle());
        }
        ArrayList<String> listOfModuleValues = new ArrayList<>(moduleNames);
        ObservableList<String> items = FXCollections.observableArrayList((listOfModuleValues));
        modulesList.setItems(items);  // Setting items in modules list
    }

    // Method handling addition of selected course by user
    public void addCourseBtn() {
        DatabaseConnection.enrollUserInCourse(selectedUser, courseListSelection.getSelectionModel().getSelectedItem());
        Alert addedAlert = new Alert(Alert.AlertType.NONE);
        addedAlert.setAlertType(Alert.AlertType.INFORMATION);
        addedAlert.setContentText("Successfully added to course");  // Alerting about successful course addition
        addedAlert.show();
    }

    // Method handling removal of selected course
    public void removeCourse() {
        DatabaseConnection.unenrollUserInCourse(selectedUser, selectedCourse);
        Alert addedAlert = new Alert(Alert.AlertType.NONE);
        addedAlert.setAlertType(Alert.AlertType.INFORMATION);
        addedAlert.setContentText("Successfully removed from the course");  // Alerting about successful course removal
        addedAlert.show();
    }

    // Method handling scene transition to the main screen
    public void changeSceneToMain(MouseEvent mouseEvent) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Users.fxml")));
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        window.setScene(scene);  // Setting the new scene
        window.show();
    }
}
