package com.codecademy.controllers;

import com.codecademy.database.DatabaseConnection;
import com.codecademy.models.User;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import java.util.*;


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

        HashMap<String, String> userNames = new HashMap<>();
        HashMap<String, String> userEmails = new HashMap<>();
        HashMap<String, String> userDateOfBirths = new HashMap<>();
        HashMap<String, String> userGenders = new HashMap<>();
        HashMap<String, String> userAddresses = new HashMap<>();
        HashMap<String, String> userZips = new HashMap<>();
        HashMap<String, String> userCountries = new HashMap<>();

        for (User user : users) {
            String email = user.getEmail();
            userNames.put(email, user.getName());
            userEmails.put(email,user.getEmail());
            userDateOfBirths.put(email,user.getDateOfBirth());
            userGenders.put(email,user.getGender());
            userAddresses.put(email,user.getAddress());
            userZips.put(email,user.getZip());
            userCountries.put(email,user.getCountry());
        }

        Collection<String> values = userEmails.values();

//Creating an ArrayList of values

        ArrayList<String> listOfValues = new ArrayList<String>(values);
        ObservableList<String> items = FXCollections.observableArrayList((listOfValues));
        userList.setItems(items);
//
        userList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                selectedUser = userList.getSelectionModel().getSelectedItem();

                nameField.setText(userNames.get(selectedUser));
                emailField.setText(selectedUser);
                dobField.setText(userDateOfBirths.get(selectedUser));
                genderField.setText(userGenders.get(selectedUser));
                addressField.setText(userAddresses.get(selectedUser));
                zipField.setText(userZips.get(selectedUser));
                countryField.setText(userCountries.get(selectedUser));

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

    public void clearFields() {
        nameField.clear();
        nameField.setPromptText("name");
        emailField.clear();
        emailField.setPromptText("email");
        dobField.clear();
        dobField.setPromptText("date of birth");
        genderField.clear();
        genderField.setPromptText("gender");
        addressField.clear();
        addressField.setPromptText("address");
        zipField.clear();
        zipField.setPromptText("zip");
        countryField.clear();
        countryField.setPromptText("country");
    }

    public void addUser(ActionEvent event) {
        selectedUser = null;
        clearFields();
        userList.getSelectionModel().clearSelection();
    }

    public void saveUser(ActionEvent event) {
        String gender;
        if (genderField.getText().equals("male")) {
            gender = "1";
        } else {
            gender = "0";
        }

        User user = new User(nameField.getText(), emailField.getText(), dobField.getText(), gender, addressField.getText(), zipField.getText(), countryField.getText());

        if (selectedUser == null) {
            DatabaseConnection.addUser(user);
        } else {
//            DatabaseConnection.updateUser(selectedUser, user);
        }
    }
}
