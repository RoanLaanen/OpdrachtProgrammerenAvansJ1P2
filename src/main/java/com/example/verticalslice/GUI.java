package com.example.verticalslice;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;

import java.util.concurrent.atomic.AtomicInteger;

public class GUI extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle(DatabaseConnection.cursusArray.get(0).getCursusNaam());

        ComboBox<String> comboBox = new ComboBox<>(FXCollections.observableArrayList(DatabaseConnection.cursusArray.get(0).getCursusNaam()));
        comboBox.getSelectionModel().selectFirst();
        comboBox.prefWidthProperty().bind(stage.widthProperty().divide(3));

        HBox dropdownContainer = new HBox(comboBox);
            dropdownContainer.setAlignment(Pos.CENTER);
            dropdownContainer.setPrefHeight(50);

        Label title = new Label(DatabaseConnection.cursusArray.get(0).getCursusNaam());
            title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        Label onderwerp = new Label(DatabaseConnection.cursusArray.get(0).getOnderwerp());
        onderwerp.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        Label niveau = new Label(DatabaseConnection.cursusArray.get(0).getNiveau());
            niveau.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        Text introductieTekst = new Text(DatabaseConnection.cursusArray.get(0).getIntroductieTekst());
            introductieTekst.setStyle("-fx-font-size: 14px;");
            introductieTekst.setWrappingWidth(640);

        HBox titleContainer = new HBox(title);
            titleContainer.setPrefHeight(50);
            titleContainer.setAlignment(Pos.CENTER);

        HBox onderwerpContainer = new HBox(onderwerp);
            onderwerpContainer.setPrefSize(320, 50);
            onderwerpContainer.setAlignment(Pos.CENTER);
        HBox niveauContainer = new HBox(niveau);
            niveauContainer.setPrefSize(320, 50);
            niveauContainer.setAlignment(Pos.CENTER);
        HBox dualContainer = new HBox(onderwerpContainer, niveauContainer);
            dualContainer.setPrefHeight(50);
            dualContainer.setAlignment(Pos.CENTER);
        HBox introductieTekstContainer = new HBox(introductieTekst);
            introductieTekstContainer.setAlignment(Pos.CENTER);
            HBox.setMargin(introductieTekst, new Insets(40));



        VBox mainContent = new VBox(titleContainer, dualContainer, introductieTekstContainer);

        Button addButton = new Button("Add");
            HBox.setHgrow(addButton, Priority.ALWAYS);
            addButton.setMaxWidth(Double.MAX_VALUE);
            addButton.setAlignment(Pos.CENTER);



        // save button 💀
//        AtomicInteger i = new AtomicInteger();
//        addButton.setOnAction(e -> {
//            comboBox.getItems().add("Option " + i.get());
//            i.getAndIncrement();
//            System.out.println(i.get());
//        });

        Button editButton = new Button("Edit");
            HBox.setHgrow(editButton, Priority.ALWAYS);
            editButton.setMaxWidth(Double.MAX_VALUE);
            editButton.setAlignment(Pos.CENTER);

        Button deleteButton = new Button("Delete");
            HBox.setHgrow(deleteButton, Priority.ALWAYS);
            deleteButton.setMaxWidth(Double.MAX_VALUE);
            deleteButton.setAlignment(Pos.CENTER);

        deleteButton.setOnAction(e -> {
            String selectedValue = comboBox.getValue();
            comboBox.getItems().remove(selectedValue);

            /*
            REMOVE FROM DATABASE
             */

        });

        HBox buttonContainer = new HBox(addButton, editButton, deleteButton);
            buttonContainer.setPrefHeight(50);
            buttonContainer.setAlignment(Pos.CENTER);

        // create a BorderPane
        BorderPane border_pane = new BorderPane(mainContent,
                dropdownContainer, null, buttonContainer, null);
        border_pane.setStyle("-fx-background-color: #FAF9F6;");

        Scene scene = new Scene(border_pane, 720, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
            launch(args);
    }
}