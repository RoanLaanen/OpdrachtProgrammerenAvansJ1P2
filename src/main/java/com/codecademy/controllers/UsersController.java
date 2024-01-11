package com.codecademy.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class UsersController implements Initializable {
    @FXML
    public ListView<String> userList;
    private Stage primaryStage;
    @FXML
    private TreeView treeView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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
        ObservableList<String> items = FXCollections.observableArrayList("test1, Test2");
            userList.setItems(items);

            Stage window = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
    }
}
