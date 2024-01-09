package com.codecademy;


import com.codecademy.database.DatabaseConnection;
import com.codecademy.views.MainWindow;

public class Main {
    public static void main(String[] args) {
        DatabaseConnection.updateCursusArray();
        MainWindow.main(args);
    }
}
