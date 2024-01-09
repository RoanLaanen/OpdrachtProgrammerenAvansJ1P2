package com.codecademy.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.ContextMenuEvent;
import javafx.stage.Stage;

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
}
