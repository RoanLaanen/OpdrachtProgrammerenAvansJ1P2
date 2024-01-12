package com.codecademy.controllers;

import com.codecademy.DataSingleton;
import com.codecademy.database.DatabaseConnection;
import com.codecademy.models.Course;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class UserCoursesController implements Initializable {

    String selectedUser;
    String selectedCourse;
    DataSingleton data = DataSingleton.getInstance();

    public Label courseNameUser;
    public Label introTextUser;
    public Label topicUser;
    public Label levelUser;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        selectedUser = data.getSelectedUser();
        selectedCourse = data.getSelectedCourse();
        Course selectedCoursesObject = DatabaseConnection.getCourseFromName(selectedCourse);
        courseNameUser.setText(selectedCoursesObject.getCourseName());
        introTextUser.setText(selectedCoursesObject.getIntroText());
        topicUser.setText(selectedCoursesObject.getTopic());
        levelUser.setText(selectedCoursesObject.getLevel());



    }


    public void changeSceneToMain(MouseEvent mouseEvent) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/MainWindow.fxml")));
        Scene scene = new Scene(parent);

        Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

}
