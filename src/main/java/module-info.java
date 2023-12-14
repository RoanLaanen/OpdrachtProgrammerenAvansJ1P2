module com.example.verticalslice {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens com.example.verticalslice to javafx.fxml;
    exports com.example.verticalslice;
}