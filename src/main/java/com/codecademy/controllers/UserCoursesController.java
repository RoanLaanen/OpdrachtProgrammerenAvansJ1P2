package com.codecademy.controllers;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class UserCoursesController implements Initializable {

    String selectedUser;
    String selectedCourse;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        ArrayList<String> data = (ArrayList<String>) stage.getUserData();
//        selectedUser = data.get(0);
//        selectedCourse = data.get(1);
//        System.out.println(selectedCourse);
//        System.out.println(selectedUser);
    }


    public void changeSceneToMain(MouseEvent mouseEvent) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/MainWindow.fxml")));
        Scene scene = new Scene(parent);

        Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

}
