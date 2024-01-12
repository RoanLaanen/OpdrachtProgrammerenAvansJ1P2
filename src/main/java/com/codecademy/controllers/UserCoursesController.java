package com.codecademy.controllers;

import com.codecademy.DataSingleton;
import com.codecademy.database.DatabaseConnection;
import com.codecademy.models.Course;
import com.codecademy.models.Module;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class UserCoursesController implements Initializable {

    public ListView<String> modulesList;
    String selectedUser;
    String selectedCourse;
    DataSingleton data = DataSingleton.getInstance();

    public Label courseNameUser;
    public Label introTextUser;
    public Label topicUser;
    public Label levelUser;
    public ComboBox courseListSelection;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        selectedUser = data.getSelectedUser();
        selectedCourse = data.getSelectedCourse();
        Course selectedCoursesObject = DatabaseConnection.getCourseFromName(selectedCourse);
        courseNameUser.setText(selectedCoursesObject.getCourseName());
        introTextUser.setText(selectedCoursesObject.getIntroText());
        topicUser.setText(selectedCoursesObject.getTopic());
        levelUser.setText(selectedCoursesObject.getLevel());
        ArrayList<Course> courses = DatabaseConnection.getAllCoursesWhereNotEnrolled(selectedUser);
        for (Course course : courses) {
            courseListSelection.getItems().add(course.getCourseName());
        }


        ArrayList<Module> modules;
        ArrayList<String> moduleNames = null;
        modules = DatabaseConnection.getModulesForCourse(selectedCourse);
        for (Module module : modules){
            assert false;
            moduleNames.add(module.getTitle());
        }
        if(moduleNames == null) {
            ArrayList<String> listOfModuleValues = new ArrayList<>(moduleNames);
            ObservableList<String> items = FXCollections.observableArrayList((listOfModuleValues));
            modulesList.setItems(items);
        }
        else{
            System.out.println("Error: ");
        }
    }

    public void addCourseBtn() {
        DatabaseConnection.enrollUserInCourse(selectedUser, (String) courseListSelection.getSelectionModel().getSelectedItem());
        Alert addedAlert = new Alert(Alert.AlertType.NONE);
        addedAlert.setAlertType(Alert.AlertType.INFORMATION);
        addedAlert.setContentText("Successfully added to course");
        addedAlert.show();
    }

    public void changeSceneToUsers(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Users.fxml")));
        Scene scene = new Scene(parent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    public void removeCourse() {
        DatabaseConnection.unenrollUserInCourse(selectedUser, selectedCourse);
        Alert addedAlert = new Alert(Alert.AlertType.NONE);
        addedAlert.setAlertType(Alert.AlertType.INFORMATION);
        addedAlert.setContentText("Successfully removed from the course");
        addedAlert.show();
    }






    public void changeSceneToMain(MouseEvent mouseEvent) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Users.fxml")));
        Scene scene = new Scene(parent);

        Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}
