package com.codecademy;


public class DataSingleton {
    private static final DataSingleton instance = new DataSingleton();
    private String selectedUser;
    private String selectedCourse;

    private DataSingleton(){}

    public static DataSingleton getInstance(){
        return instance;
    }

    public String getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(String selectedUser) {
        this.selectedUser = selectedUser;
    }
    public String getSelectedCourse() {
        return selectedCourse;
    }

    public void setSelectedCourse(String selectedCourse) {
        this.selectedCourse = selectedCourse;
    }

}
