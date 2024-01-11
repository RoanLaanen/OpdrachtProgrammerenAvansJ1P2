package com.codecademy.controllers;

import com.codecademy.database.DatabaseConnection;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;


public class UsersController implements Initializable {
    @FXML
    public ListView<String> userList;
    String selectedUser;
    public TextField nameField;
    public TextField emailField;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String> userEmails = new ArrayList<>();
        userEmails = DatabaseConnection.getAllUserEmails();
        ObservableList<String> items = FXCollections.observableArrayList(userEmails);
        userList.setItems(items);
//
        userList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                selectedUser = userList.getSelectionModel().getSelectedItem();
                emailField.setText(selectedUser);
            }
        });
    }

    public void setPrimaryStage(Stage primaryStage) {
    }

    public void selectItem() {
    }

    public void changeSceneToMain(MouseEvent mouseEvent)
        throws IOException {
            Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/MainWindow.fxml")));
            Scene scene = new Scene(parent);

            Stage window = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
    }
}
