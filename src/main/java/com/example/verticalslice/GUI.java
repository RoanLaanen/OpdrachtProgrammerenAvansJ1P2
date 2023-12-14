package com.example.verticalslice;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;

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

        // create a label
        HBox dropdownContainer = new HBox();
        dropdownContainer.setStyle("-fx-background-color: #70d900;");
        dropdownContainer.setPrefHeight(50);
        HBox mainContent = new HBox();
        mainContent.setStyle("-fx-background-color: #b100ec;");
        HBox buttonContainer = new HBox();
        buttonContainer.setStyle("-fx-background-color: #0012d9;");
        buttonContainer.setPrefHeight(50);






        // create a BorderPane
        BorderPane border_pane = new BorderPane(mainContent,
                dropdownContainer, null, buttonContainer, null);

        // set alignment
        border_pane.setAlignment(dropdownContainer, Pos.CENTER);
        border_pane.setAlignment(buttonContainer, Pos.CENTER);

        // create a scene
        Scene scene = new Scene(border_pane, 640, 480);

        // set the scene
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
            launch(args);
    }
}