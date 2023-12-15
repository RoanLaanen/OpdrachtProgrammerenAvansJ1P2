package com.example.verticalslice;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class GUI extends Application {

    Scene scene, scene_add, scene_edit;
    public int selectedIndex = 0;

    @Override
    public void start(Stage stage) {
        stage.setTitle(DatabaseConnection.cursusArray.get(selectedIndex).getCursusNaam());

        ComboBox<String> comboBox = new ComboBox<>(FXCollections.observableArrayList(DatabaseConnection.cursusNaamArray));
        comboBox.getSelectionModel().selectFirst();
        comboBox.prefWidthProperty().bind(stage.widthProperty().divide(3));
        HBox dropdownContainer = new HBox(comboBox);
        dropdownContainer.setAlignment(Pos.CENTER);
        dropdownContainer.setPrefHeight(50);
        dropdownContainer.setStyle("-fx-border-color: #000000; -fx-border-width: 0 0 1 0;");


        Label title = new Label(DatabaseConnection.cursusArray.get(selectedIndex).getCursusNaam());
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Label topic = new Label(DatabaseConnection.cursusArray.get(selectedIndex).getOnderwerp());
        topic.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        String levelString = DatabaseConnection.cursusArray.get(selectedIndex).getNiveau();
        if (levelString.equals("niks")) {
            levelString = "";
        }
        Label level = new Label(levelString);
        level.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        Text introText = new Text(DatabaseConnection.cursusArray.get(selectedIndex).getIntroductieTekst());
        introText.setStyle("-fx-font-size: 14px;");
        introText.setWrappingWidth(640);
        comboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                selectedIndex = DatabaseConnection.cursusNaamArray.indexOf(t1);
                if (DatabaseConnection.cursusNaamArray.size() == 1) {
                    selectedIndex = 0;
                }
                title.setText(DatabaseConnection.cursusArray.get(selectedIndex).getCursusNaam());
                stage.setTitle(DatabaseConnection.cursusArray.get(selectedIndex).getCursusNaam());
                topic.setText(DatabaseConnection.cursusArray.get(selectedIndex).getOnderwerp());
                String levelString = DatabaseConnection.cursusArray.get(selectedIndex).getNiveau();
                if (levelString.equals("niks")) {
                    levelString = "";
                }
                level.setText(levelString);
                introText.setText(DatabaseConnection.cursusArray.get(selectedIndex).getIntroductieTekst());
            }
        });

        HBox titleContainer = new HBox(title);
        titleContainer.setPrefHeight(50);
        titleContainer.setAlignment(Pos.CENTER);
        HBox topicContainer = new HBox(topic);
        topicContainer.setPrefSize(320, 50);
        topicContainer.setAlignment(Pos.CENTER);
        HBox levelContainer = new HBox(level);
        levelContainer.setPrefSize(320, 50);
        levelContainer.setAlignment(Pos.CENTER);
        HBox dualContainer = new HBox(topicContainer, levelContainer);
        dualContainer.setPrefHeight(50);
        dualContainer.setAlignment(Pos.CENTER);
        HBox introTextContainer = new HBox(introText);
        introTextContainer.setAlignment(Pos.CENTER);
        HBox.setMargin(introText, new Insets(40));
        introTextContainer.setStyle("-fx-border-color: black; -fx-border-width: 0 0 1 0;");

        VBox mainContent = new VBox(titleContainer, dualContainer, introTextContainer);

        Button addButton = new Button("Add");
        HBox.setHgrow(addButton, Priority.ALWAYS);
        addButton.setMaxWidth(Double.MAX_VALUE);
        addButton.setAlignment(Pos.CENTER);
        addButton.setOnAction(e -> stage.setScene(scene_add));
        addButton.setMaxSize(210, 60);
        Button editButton = new Button("Edit");
        HBox.setHgrow(editButton, Priority.ALWAYS);
        editButton.setMaxWidth(Double.MAX_VALUE);
        editButton.setAlignment(Pos.CENTER);
        editButton.setOnAction(e -> stage.setScene(scene_edit));
        editButton.setMaxSize(210, 60);

        Button deleteButton = new Button("Delete");
        HBox.setHgrow(deleteButton, Priority.ALWAYS);
        deleteButton.setMaxWidth(Double.MAX_VALUE);
        deleteButton.setAlignment(Pos.CENTER);
        deleteButton.setMaxSize(210, 60);

        deleteButton.setOnAction(e -> {
            if (Objects.equals(DatabaseConnection.cursusArray.get(selectedIndex).getCursusNaam(), "")) {
                return;
            }
            comboBox.getItems().remove(selectedIndex);
            DatabaseConnection.deleteCursus(DatabaseConnection.cursusArray.get(selectedIndex).getCursusNaam());
            if (comboBox.getItems().isEmpty()) {
                comboBox.getItems().add("Geen resultaten gevonden");
                DatabaseConnection.cursusNaamArray.add("Geen resultaten gevonden");
                DatabaseConnection.cursusArray.add(new Cursus("", "", "", Cursus.niveau.niks));
            }
            comboBox.getSelectionModel().selectFirst();
        });

        HBox buttonContainer = new HBox(addButton, editButton, deleteButton);
        buttonContainer.setPrefHeight(120);
        buttonContainer.setAlignment(Pos.CENTER);

        BorderPane border_pane = new BorderPane(mainContent, dropdownContainer, null, buttonContainer, null);
        border_pane.setStyle("-fx-background-color: #FAF9F6;");

        scene = new Scene(border_pane, 720, 480);


//        ----------------------------------- END OF SCENE MAIN -----------------------------------
//        ----------------------------------- START SCENE ADD -----------------------------------

        TextField title_add = new TextField();
        title_add.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        title_add.setPromptText("title");
        TextField topic_add = new TextField();
        topic_add.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        topic_add.setPromptText("onderwerp");
        ComboBox<String> levelBox = new ComboBox<>(FXCollections.observableArrayList("Beginner", "Gevorderd", "Expert"));
        TextArea introText_add = new TextArea();
        introText_add.setStyle("-fx-font-size: 14px;");
        introText_add.setWrapText(true);
        introText_add.setPrefWidth(640);
        introText_add.setPromptText("introductie tekst");

        HBox titleContainer_add = new HBox(title_add);
        titleContainer_add.setPrefHeight(50);
        titleContainer_add.setAlignment(Pos.CENTER);
        HBox topicContainer_add = new HBox(topic_add);
        topicContainer_add.setPrefSize(320, 50);
        topicContainer_add.setAlignment(Pos.CENTER);
        HBox levelContainer_add = new HBox(levelBox);
        levelContainer_add.setPrefSize(320, 50);
        levelContainer_add.setAlignment(Pos.CENTER);
        HBox dualContainer_add = new HBox(topicContainer_add, levelContainer_add);
        dualContainer_add.setPrefHeight(50);
        dualContainer_add.setAlignment(Pos.CENTER);
        HBox introTextContainer_add = new HBox(introText_add);
        introTextContainer_add.setAlignment(Pos.CENTER);
        HBox.setMargin(introText_add, new Insets(40));

        VBox mainContent_add = new VBox(titleContainer_add, dualContainer_add, introTextContainer_add);


        Button saveButton = new Button("Save");
        HBox.setHgrow(saveButton, Priority.ALWAYS);
        saveButton.setMaxWidth(Double.MAX_VALUE);
        saveButton.setAlignment(Pos.CENTER);
        saveButton.setMaxSize(315, 60);
        saveButton.setOnAction(e -> {
            if (!title_add.getText().trim().isEmpty() && !topic_add.getText().trim().isEmpty() && levelBox.getSelectionModel().getSelectedItem() != null && !introText_add.getText().trim().isEmpty()) {
                comboBox.getItems().add(title_add.getText());
            }
            if (DatabaseConnection.cursusNaamArray.contains("Geen resultaten gevonden")) {
                comboBox.getItems().remove("Geen resultaten gevonden");
                DatabaseConnection.cursusNaamArray.remove("Geen resultaten gevonden");
                DatabaseConnection.cursusArray.remove(0);
                DatabaseConnection.addCursus(title_add.getText(), topic_add.getText(), introText_add.getText(), levelBox.getSelectionModel().getSelectedItem());
                comboBox.selectionModelProperty().get().select(0);
            } else {
                DatabaseConnection.addCursus(title_add.getText(), topic_add.getText(), introText_add.getText(), levelBox.getSelectionModel().getSelectedItem());
            }

            title_add.clear();
            topic_add.clear();
            introText_add.clear();
            levelBox.setValue(null);
            stage.setScene(scene);
        });


        Button cancelButton = new Button("Cancel");
        HBox.setHgrow(cancelButton, Priority.ALWAYS);
        cancelButton.setMaxWidth(Double.MAX_VALUE);
        cancelButton.setAlignment(Pos.CENTER);
        cancelButton.setMaxSize(315, 60);
        cancelButton.setOnAction(e -> {
            title_add.clear();
            topic_add.clear();
            introText_add.clear();
            levelBox.setValue(null);
            stage.setScene(scene);
        });

        HBox buttonContainer_add = new HBox(saveButton, cancelButton);
        buttonContainer_add.setPrefHeight(120);
        buttonContainer_add.setAlignment(Pos.CENTER);


        BorderPane border_pane_add = new BorderPane(mainContent_add, null, null, buttonContainer_add, null);
        border_pane_add.setStyle("-fx-background-color: #FAF9F6;");

        scene_add = new Scene(border_pane_add, 720, 480);


//        ----------------------------------- END OF SCENE ADD -----------------------------------
//        ----------------------------------- START SCENE EDIT -----------------------------------


        TextField title_edit = new TextField(DatabaseConnection.cursusArray.get(selectedIndex).getCursusNaam());
        title_edit.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        title_edit.setPromptText("title");
        TextField topic_edit = new TextField(DatabaseConnection.cursusArray.get(selectedIndex).getOnderwerp());
        topic_edit.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        topic_edit.setPromptText("onderwerp");
        ComboBox<String> levelBox_edit = new ComboBox<>(FXCollections.observableArrayList("Beginner", "Gevorderd", "Expert"));
        levelBox_edit.setValue(DatabaseConnection.cursusArray.get(selectedIndex).getNiveau());
        // selected data doodood
        TextArea introText_edit = new TextArea(DatabaseConnection.cursusArray.get(selectedIndex).getIntroductieTekst());
        introText_edit.setStyle("-fx-font-size: 14px;");
        introText_edit.setWrapText(true);
        introText_edit.setPrefWidth(640);
        introText_edit.setPromptText("introductie tekst");

        HBox titleContainer_edit = new HBox(title_edit);
        titleContainer_edit.setPrefHeight(50);
        titleContainer_edit.setAlignment(Pos.CENTER);
        HBox topicContainer_edit = new HBox(topic_edit);
        topicContainer_edit.setPrefSize(320, 50);
        topicContainer_edit.setAlignment(Pos.CENTER);
        HBox levelContainer_edit = new HBox(levelBox_edit);
        levelContainer_edit.setPrefSize(320, 50);
        levelContainer_edit.setAlignment(Pos.CENTER);
        HBox dualContainer_edit = new HBox(topicContainer_edit, levelContainer_edit);
        dualContainer_edit.setPrefHeight(50);
        dualContainer_edit.setAlignment(Pos.CENTER);
        HBox introTextContainer_edit = new HBox(introText_edit);
        introTextContainer_edit.setAlignment(Pos.CENTER);
        HBox.setMargin(introText_edit, new Insets(40));

        VBox mainContent_edit = new VBox(titleContainer_edit, dualContainer_edit, introTextContainer_edit);


        Button saveButton_edit = new Button("Save");
        HBox.setHgrow(saveButton_edit, Priority.ALWAYS);
        saveButton_edit.setMaxWidth(Double.MAX_VALUE);
        saveButton_edit.setAlignment(Pos.CENTER);
        saveButton_edit.setMaxSize(315, 60);
        saveButton_edit.setOnAction(e -> {
            if (!title_edit.getText().trim().isEmpty() && !topic_edit.getText().trim().isEmpty() && levelBox_edit.getSelectionModel().getSelectedItem() != null && !introText_edit.getText().trim().isEmpty()) {
                /*
                FIX HIER DAT HIJ IN LIJSTJE VERANDERD NEEF
                 */
            }


            /*


            EDIT ALLES IN DATABASE
            RELOAD OOK DIE MAIN PAGINA OFZ IDK

             */

            title_add.clear();
            topic_add.clear();
            introText_add.clear();
            levelBox.setValue(null);
            stage.setScene(scene);
        });


        Button cancelButton_edit = new Button("Cancel");
        HBox.setHgrow(cancelButton_edit, Priority.ALWAYS);
        cancelButton_edit.setMaxWidth(Double.MAX_VALUE);
        cancelButton_edit.setAlignment(Pos.CENTER);
        cancelButton_edit.setMaxSize(315, 60);
        cancelButton_edit.setOnAction(e -> {
            title_add.clear();
            topic_add.clear();
            introText_add.clear();
            levelBox.setValue(null);
            stage.setScene(scene);
        });

        HBox buttonContainer_edit = new HBox(saveButton_edit, cancelButton_edit);
        buttonContainer_edit.setPrefHeight(120);
        buttonContainer_edit.setAlignment(Pos.CENTER);


        BorderPane border_pane_edit = new BorderPane(mainContent_edit, null, null, buttonContainer_edit, null);
        border_pane_edit.setStyle("-fx-background-color: #FAF9F6;");

        scene_edit = new Scene(border_pane_edit, 720, 480);


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