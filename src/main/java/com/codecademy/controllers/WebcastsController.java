package com.codecademy.controllers;

import com.codecademy.database.DatabaseConnection;
import com.codecademy.models.Webcast;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class WebcastsController implements Initializable {

    @FXML
    public ListView<String> webcastList;  // ListView component for displaying the webcasts.
    @FXML
    public Label mostViewed1;  // Label component to display the most viewed webcast title #1
    @FXML
    public Label mostViewed2;  // Label component to display the most viewed webcast title #2
    @FXML
    public Label mostViewed3;  // Label component to display the most viewed webcast title #3

    /*
    This method is called after all the @FXML annotated variables have been injected
    and the root element of the associated view has been fully processed.
    It initializes the UI elements of Webcast.
    */
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<Webcast> webcasts = DatabaseConnection.getWebcasts();  // Getting webcasts from database.
        ArrayList<Webcast> topWebcasts = DatabaseConnection.getTopWebcasts();
        // Extracting the titles of top 3 most viewed webcasts.
        String mostViewed1Title = topWebcasts.get(0).getTitle();
        String mostViewed2Title = topWebcasts.get(1).getTitle();
        String mostViewed3Title = topWebcasts.get(2).getTitle();

        ArrayList<String> webcastNames = new ArrayList<>(); // ArrayList to store webcastNames.

        // Setting the text of the labels to the webcasts titles.
        mostViewed1.setText("1: " + mostViewed1Title);
        mostViewed2.setText("2: " + mostViewed2Title);
        mostViewed3.setText("3: " + mostViewed3Title);

        // Generating list of webcast names for ListView.
        for (Webcast webcast : webcasts) {
            webcastNames.add(webcast.getTitle());
        }

        // Creating an observable list and setting it as the content of the ListView.
        ObservableList<String> items = FXCollections.observableArrayList(webcastNames);
        webcastList.setItems(items);
    }

    /*
    This method is triggered when a MouseEvent occurs.
    It changes the current scene to main window scene.
    */
    public void changeSceneToMain(MouseEvent mouseEvent) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/MainWindow.fxml")));
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}
