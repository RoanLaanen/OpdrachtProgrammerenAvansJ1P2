package com.codecademy.controllers;

import com.codecademy.database.DatabaseConnection;
import com.codecademy.models.Level;
import com.codecademy.models.Module;
import com.codecademy.models.Webcast;
import javafx.beans.binding.Bindings;
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
    public ListView webcastList;

    @FXML
    public Label mostViewed1;
    @FXML
    public Label mostViewed2;
    @FXML
    public Label mostViewed3;
    public void initialize(URL location, ResourceBundle resources) {

        ArrayList<Webcast> webcasts = DatabaseConnection.getWebcasts();
        String mostViewed1Title = webcasts.get(0).getTitle();
        String mostViewed2Title = webcasts.get(1).getTitle();
        String mostViewed3Title = webcasts.get(2).getTitle();
        ArrayList<String> webcastNames = new ArrayList<String>();
        mostViewed1.setText("1: " + mostViewed1Title);
        mostViewed2.setText("2: " + mostViewed2Title);
        mostViewed3.setText("3: " + mostViewed3Title);
        for (Webcast webcast : webcasts) {
          webcastNames.add(webcast.getTitle());
        }



        ArrayList<String> listOfWebcastValues = new ArrayList<>(webcastNames);
        ObservableList<String> items = FXCollections.observableArrayList((listOfWebcastValues));
        webcastList.setItems(items);


    }
    public void changeSceneToMain(MouseEvent mouseEvent) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/MainWindow.fxml")));
        Scene scene = new Scene(parent);

        Stage window = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }


}
