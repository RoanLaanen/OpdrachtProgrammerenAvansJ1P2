package com.codecademy.models;

public class Certificate {
    private int certificateID;
    private float grade;
    private String nameEmployee;
    private String email;
    private String courseName;
    public Certificate(int certificateID,float grade, String nameEmployee,String email,String courseName){
        this.certificateID = certificateID;
        this.grade = grade;
        this.nameEmployee = nameEmployee;
        this.email = email;
        this.courseName = courseName;
    }

    public int getCertificateID() {
        return certificateID;
    }

    public float getGrade() {
        return grade;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getEmail() {
        return email;
    }

    public String getNameEmployee() {
        return nameEmployee;
    }
}
