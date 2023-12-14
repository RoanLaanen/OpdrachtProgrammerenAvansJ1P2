package com.example.verticalslice;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.geometry.Insets;

public class GUI extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Hello World!");


        Label title = new Label(Main.cursus1.getCursusNaam());
            title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        HBox titleHBox = new HBox(title);
            titleHBox.setPrefHeight(50);
            titleHBox.setPrefWidth(480);
            titleHBox.setAlignment(Pos.CENTER);
            titleHBox.setStyle("-fx-background-color: #A8D1D1;");
//        mainPane.setStyle("-fx-background-color: #9EA1D4;");
        Label onderwerp = new Label(Main.cursus1.getOnderwerp());
        Label niveau = new Label(Main.cursus1.getNiveau());
        Text introductieTekst = new Text(Main.cursus1.getIntroductieTekst());

        introductieTekst.setWrappingWidth(480);
        HBox onderwerpHBox = new HBox(onderwerp);
            onderwerpHBox.setAlignment(Pos.CENTER);
            onderwerpHBox.setPrefWidth(240);
            onderwerpHBox.setStyle("-fx-background-color: #D8D1D1;");

        HBox niveauHBox = new HBox(niveau);
            niveauHBox.setAlignment(Pos.CENTER);
            niveauHBox.setPrefWidth(240);
            niveauHBox.setStyle("-fx-background-color: #F8D3A1;");

        HBox row2 = new HBox(onderwerpHBox, niveauHBox);
            row2.setPrefWidth(480);
            row2.setAlignment(Pos.CENTER);
            row2.setStyle("-fx-background-color: #A8D1D1;");
        HBox row3 = new HBox(introductieTekst);
            row3.setStyle("-fx-background-color: #B8D1D1;");
            row3.setPrefHeight(100);
            row3.setMaxWidth(480);

        VBox rows = new VBox(10, titleHBox, row2, row3);
        StackPane.setMargin(rows, new Insets(10));

        StackPane root = new StackPane(rows);

//        mainPane.add(titleHBox, 0, 0);
//        mainPane.add(onderwerpHBox, 0, 1);


        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}