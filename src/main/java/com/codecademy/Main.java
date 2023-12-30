package com.codecademy;


import com.codecademy.database.DatabaseConnection;
import com.codecademy.views.GUI;

public class Main {
    public static void main(String[] args) {
        DatabaseConnection.updateCursusArray();
        GUI.main(args);
    }
}
