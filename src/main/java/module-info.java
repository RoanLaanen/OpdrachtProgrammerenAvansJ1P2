module com.codecademy {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.microsoft.sqlserver.jdbc;


    exports com.codecademy.models;
    opens com.codecademy.models to javafx.fxml;
    exports com.codecademy.views;
    opens com.codecademy.views to javafx.fxml;
    exports com.codecademy.controllers;
    opens com.codecademy.controllers to javafx.fxml;
    exports com.codecademy.database;
    opens com.codecademy.database to javafx.fxml;
    exports com.codecademy;
    opens com.codecademy to javafx.fxml;
}