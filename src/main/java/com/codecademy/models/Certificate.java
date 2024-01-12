package com.codecademy.models;

public class Certificate {
    private final int certificateID;
    private String email;
    private String courseName;
    public Certificate(int certificateID, String email, String courseName){
        this.certificateID = certificateID;
        this.email = email;
        this.courseName = courseName;
    }

    public int getCertificateID() {
        return certificateID;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getEmail() {
        return email;
    }
}
