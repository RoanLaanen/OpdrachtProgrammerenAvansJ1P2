package com.example.verticalslice;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;

import java.util.concurrent.atomic.AtomicInteger;

public class GUI extends Application {

    Scene scene, scene2, scene3;

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
            addButton.setOnAction(e -> {
                stage.setScene(scene2);

            });


        Button editButton = new Button("Edit");
            HBox.setHgrow(editButton, Priority.ALWAYS);
            editButton.setMaxWidth(Double.MAX_VALUE);
            editButton.setAlignment(Pos.CENTER);
            editButton.setOnAction(e -> {
                stage.setScene(scene3);

            });

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

        BorderPane border_pane = new BorderPane(mainContent,  dropdownContainer, null, buttonContainer, null);
            border_pane.setStyle("-fx-background-color: #FAF9F6;");

        scene = new Scene(border_pane, 720, 480);


//        ----------------------------------- END OF SCENE 1 -----------------------------------

        TextField titleField = new TextField();
            titleField.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
            titleField.setPromptText("title");
        TextField onderwerpField = new TextField();
            onderwerpField.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
            onderwerpField.setPromptText("onderwerp");
        ComboBox<String> niveauBox = new ComboBox<>(FXCollections.observableArrayList("Beginner", "Gevorderd", "Expert"));
        TextArea introductieArea = new TextArea();
            introductieArea.setStyle("-fx-font-size: 14px;");
            introductieArea.setWrapText(true);
            introductieArea.setPrefWidth(640);
            introductieArea.setPromptText("introductie tekst");

        HBox titleFieldContainer = new HBox(titleField);
            titleFieldContainer.setPrefHeight(50);
            titleFieldContainer.setAlignment(Pos.CENTER);
        HBox onderwerpFieldContainer = new HBox(onderwerpField);
            onderwerpFieldContainer.setPrefSize(320, 50);
            onderwerpFieldContainer.setAlignment(Pos.CENTER);
        HBox niveauFieldContainer = new HBox(niveauBox);
            niveauFieldContainer.setPrefSize(320, 50);
            niveauFieldContainer.setAlignment(Pos.CENTER);
        HBox dualContainer2 = new HBox(onderwerpFieldContainer, niveauFieldContainer);
            dualContainer2.setPrefHeight(50);
            dualContainer2.setAlignment(Pos.CENTER);
        HBox introductieAreaContainer = new HBox(introductieArea);
            introductieAreaContainer.setAlignment(Pos.CENTER);
            HBox.setMargin(introductieArea, new Insets(40));

        VBox mainContent2 = new VBox(titleFieldContainer, dualContainer2, introductieAreaContainer);


        Button saveButton = new Button("Save");
        HBox.setHgrow(saveButton, Priority.ALWAYS);
        saveButton.setMaxWidth(Double.MAX_VALUE);
        saveButton.setAlignment(Pos.CENTER);
        saveButton.setOnAction(e -> {
            if (!titleField.getText().trim().isEmpty() && !onderwerpField.getText().trim().isEmpty() &&
                    niveauBox.getSelectionModel().getSelectedItem() != null && !introductieArea.getText().trim().isEmpty()) {
                comboBox.getItems().add(titleField.getText());
            }


            /*


            ADD ALLES TO DATABASE


             */

            titleField.clear();
            onderwerpField.clear();
            introductieArea.clear();
            niveauBox.setValue(null);
            stage.setScene(scene);
        });


        Button cancelButton = new Button("Cancel");
        HBox.setHgrow(cancelButton, Priority.ALWAYS);
        cancelButton.setMaxWidth(Double.MAX_VALUE);
        cancelButton.setAlignment(Pos.CENTER);
        cancelButton.setOnAction(e -> {
            titleField.clear();
            onderwerpField.clear();
            introductieArea.clear();
            niveauBox.setValue(null);
            stage.setScene(scene);
        });

        HBox buttonContainer1 = new HBox(saveButton, cancelButton);
        buttonContainer1.setPrefHeight(50);
        buttonContainer1.setAlignment(Pos.CENTER);


        BorderPane border_pane2 = new BorderPane(mainContent2, null, null, buttonContainer1, null);
        border_pane2.setStyle("-fx-background-color: #FAF9F6;");

        scene2 = new Scene(border_pane2, 720, 480);



//        ----------------------------------- END OF SCENE 2 -----------------------------------


        TextField titleField2 = new TextField(DatabaseConnection.cursusArray.get(0).getCursusNaam());
        titleField2.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        titleField2.setPromptText("title");
        TextField onderwerpField2 = new TextField(DatabaseConnection.cursusArray.get(0).getOnderwerp());
        onderwerpField2.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        onderwerpField2.setPromptText("onderwerp");
        ComboBox<String> niveauBox2 = new ComboBox<>(FXCollections.observableArrayList("Beginner", "Gevorderd", "Expert"));
        niveauBox2.setValue(DatabaseConnection.cursusArray.get(0).getNiveau());
        // selected data doodood
        TextArea introductieArea2 = new TextArea(DatabaseConnection.cursusArray.get(0).getIntroductieTekst());
        introductieArea2.setStyle("-fx-font-size: 14px;");
        introductieArea2.setWrapText(true);
        introductieArea2.setPrefWidth(640);
        introductieArea2.setPromptText("introductie tekst");

        HBox titleFieldContainer2 = new HBox(titleField2);
        titleFieldContainer2.setPrefHeight(50);
        titleFieldContainer2.setAlignment(Pos.CENTER);
        HBox onderwerpFieldContainer2 = new HBox(onderwerpField2);
        onderwerpFieldContainer2.setPrefSize(320, 50);
        onderwerpFieldContainer2.setAlignment(Pos.CENTER);
        HBox niveauFieldContainer2 = new HBox(niveauBox2);
        niveauFieldContainer2.setPrefSize(320, 50);
        niveauFieldContainer2.setAlignment(Pos.CENTER);
        HBox dualContainer3 = new HBox(onderwerpFieldContainer2, niveauFieldContainer2);
        dualContainer3.setPrefHeight(50);
        dualContainer3.setAlignment(Pos.CENTER);
        HBox introductieAreaContainer2 = new HBox(introductieArea2);
        introductieAreaContainer2.setAlignment(Pos.CENTER);
        HBox.setMargin(introductieArea2, new Insets(40));

        VBox mainContent3 = new VBox(titleFieldContainer2, dualContainer3, introductieAreaContainer2);


        Button saveButton2 = new Button("Save");
        HBox.setHgrow(saveButton2, Priority.ALWAYS);
        saveButton2.setMaxWidth(Double.MAX_VALUE);
        saveButton2.setAlignment(Pos.CENTER);
        saveButton2.setOnAction(e -> {
            if (!titleField2.getText().trim().isEmpty() && !onderwerpField2.getText().trim().isEmpty() &&
                    niveauBox2.getSelectionModel().getSelectedItem() != null && !introductieArea2.getText().trim().isEmpty()) {
                /*
                FIX HIER DAT HIJ IN LIJSTJE VERANDERD NEEF
                 */
            }


            /*


            EDIT ALLES IN DATABASE
            RELOAD OOK DIE MAIN PAGINA OFZ IDK

             */

            titleField.clear();
            onderwerpField.clear();
            introductieArea.clear();
            niveauBox.setValue(null);
            stage.setScene(scene);
        });


        Button cancelButton2 = new Button("Cancel");
        HBox.setHgrow(cancelButton2, Priority.ALWAYS);
        cancelButton2.setMaxWidth(Double.MAX_VALUE);
        cancelButton2.setAlignment(Pos.CENTER);
        cancelButton2.setOnAction(e -> {
            titleField.clear();
            onderwerpField.clear();
            introductieArea.clear();
            niveauBox.setValue(null);
            stage.setScene(scene);
        });

        HBox buttonContainer2 = new HBox(saveButton2, cancelButton2);
        buttonContainer2.setPrefHeight(50);
        buttonContainer2.setAlignment(Pos.CENTER);


        BorderPane border_pane3 = new BorderPane(mainContent3, null, null, buttonContainer2, null);
        border_pane3.setStyle("-fx-background-color: #FAF9F6;");

        scene3 = new Scene(border_pane3, 720, 480);













        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
            launch(args);
    }
}


/*

Donkeyy kong pls maak nog ff alles mooi enzo, sommige variable names zijn engels sommige nederlands 💀
Ook ff kijken ofje beetje korter kan maken❤️ alles is wahed dubbel maar idk is 1 uur mijn energie is loesoe

 */