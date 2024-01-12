package com.codecademy.controllers;

import com.codecademy.database.DatabaseConnection;
import com.codecademy.models.User;
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
    public TextField dobField;
    public TextField genderField;
    public TextField addressField;
    public TextField zipField;
    public TextField countryField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<User> users;
        users = DatabaseConnection.getAllUsers();

        ArrayList<String> userNames = new ArrayList<>();
        ArrayList<String> userEmails = new ArrayList<>();
        ArrayList<String> userDateOfBirths = new ArrayList<>();
        ArrayList<String> userGenders = new ArrayList<>();
        ArrayList<String> userAddresses = new ArrayList<>();
        ArrayList<String> userZips = new ArrayList<>();
        ArrayList<String> userCountries = new ArrayList<>();

        for (User user : users) {
            userNames.add(user.getName());
            userEmails.add(user.getEmail());
            userDateOfBirths.add(user.getDateOfBirth());
            userGenders.add(String.valueOf(user.getGender()));
            userAddresses.add(user.getAddress());
            userZips.add(user.getZip());
            userCountries.add(user.getCountry());
        }


        ObservableList<String> items = FXCollections.observableArrayList(userEmails);
        userList.setItems(items);
//
        userList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                selectedUser = userList.getSelectionModel().getSelectedItem();
                
                int index = userEmails.indexOf(selectedUser);

                nameField.setText(userNames.get(index));
                emailField.setText(selectedUser);
                dobField.setText(userDateOfBirths.get(index));
                genderField.setText(userGenders.get(index));
                addressField.setText(userAddresses.get(index));
                zipField.setText(userZips.get(index));
                countryField.setText(userCountries.get(index));

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

    public void deleteUser() {
        DatabaseConnection.deleteUser(selectedUser);
    }
}
