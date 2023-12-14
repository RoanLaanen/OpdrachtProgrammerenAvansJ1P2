module com.example.verticalslice {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.verticalslice to javafx.fxml;
    exports com.example.verticalslice;
}