package com.codecademy.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class UsersController implements Initializable {
    private Stage primaryStage;
    @FXML
    private TreeView treeView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TreeItem<String> user = new TreeItem<>("User");
        TreeItem<String> userData= new TreeItem<>("User Data");
        TreeItem<String> userCourses = new TreeItem<>("User Courses");
        TreeItem<String> userModules = new TreeItem<>("User Modules");
        TreeItem<String> userWebcasts = new TreeItem<>("User Webcasts");
        userCourses.getChildren().addAll(userWebcasts, userModules);
        user.getChildren().addAll(userData,userCourses);
        treeView.setRoot(user);
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void selectItem() {
    }

    public void changeSceneToMain(MouseEvent mouseEvent)
        throws IOException {
            Parent parent = FXMLLoader.load(getClass().getResource("/MainWindow.fxml"));
            Scene scene = new Scene(parent);

            Stage window = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
    }
}
