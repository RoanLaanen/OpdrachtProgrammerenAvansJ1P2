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

// ---------------------------------------------------------------
//        Label title = new Label(DatabaseConnection.cursusArray.get(0).getCursusNaam());
//            title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
//
//        HBox titleHBox = new HBox(title);
//            titleHBox.setPrefHeight(50);
//            titleHBox.setPrefWidth(480);
//            titleHBox.setAlignment(Pos.CENTER);
////            titleHBox.setStyle("-fx-background-color: #A8D1D1;");
////        mainPane.setStyle("-fx-background-color: #9EA1D4;");
//        Label onderwerp = new Label(DatabaseConnection.cursusArray.get(0).getOnderwerp());
//        Label niveau = new Label(DatabaseConnection.cursusArray.get(0).getNiveau());
//        Text introductieTekst = new Text(DatabaseConnection.cursusArray.get(0).getIntroductieTekst());
//
//        introductieTekst.setWrappingWidth(480);
//        HBox onderwerpHBox = new HBox(onderwerp);
//            onderwerpHBox.setAlignment(Pos.CENTER);
//            onderwerpHBox.setPrefWidth(240);
////            onderwerpHBox.setStyle("-fx-background-color: #D8D1D1;");
//
//        HBox niveauHBox = new HBox(niveau);
//            niveauHBox.setAlignment(Pos.CENTER);
//            niveauHBox.setPrefWidth(240);
////            niveauHBox.setStyle("-fx-background-color: #F8D3A1;");
//
//        HBox row2 = new HBox(onderwerpHBox, niveauHBox);
//            row2.setPrefWidth(480);
//            row2.setAlignment(Pos.CENTER);
////            row2.setStyle("-fx-background-color: #A8D1D1;");
//        HBox row3 = new HBox(introductieTekst);
////            row3.setStyle("-fx-background-color: #B8D1D1;");
//            row3.setPrefHeight(100);
//            row3.setMaxWidth(480);
//
//        VBox rows = new VBox(10, titleHBox, row2, row3);
//        StackPane.setMargin(rows, new Insets(10));
// ---------------------------------------------------------------

        stage.setTitle("BorderPane");

        ComboBox<String> comboBox = new ComboBox<>(FXCollections.observableArrayList("Course1"));
        comboBox.getSelectionModel().selectFirst();
        comboBox.prefWidthProperty().bind(stage.widthProperty().divide(3));

        //comboBox.getItems().add("Option 4");



        HBox dropdownContainer = new HBox(comboBox);
            dropdownContainer.setAlignment(Pos.CENTER);
            dropdownContainer.setPrefHeight(50);

        Label title = new Label("Course1");
            title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        Label onderwerp = new Label("Onderwerp");
        onderwerp.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        Label niveau = new Label("Niveau");
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

        // set alignment
        border_pane.setAlignment(dropdownContainer, Pos.CENTER);
        border_pane.setAlignment(buttonContainer, Pos.CENTER);

        Scene scene = new Scene(border_pane, 720, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
            launch(args);
    }
}