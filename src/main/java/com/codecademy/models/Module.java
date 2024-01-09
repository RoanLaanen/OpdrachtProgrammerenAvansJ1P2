package com.codecademy.models;

public class Module {
    private String title;
    private float version;
    private String description;
    private String emailContact;
    private String nameContact;
    private final int contentID;
    private String courseName;
    public Module(String title,float version, String description,String emailContact,String nameContact,int contentID,String courseName){
        this.title = title;
        this.version = version;
        this.description = description;
        this.emailContact = emailContact;
        this.nameContact = nameContact;
        this.contentID = contentID;
        this.courseName = courseName;
    }

    public String getCourseName() {
        return courseName;
    }

    public float getVersion() {
        return version;
    }

    public int getContentID() {
        return contentID;
    }

    public String getDescription() {
        return description;
    }

    public String getEmailContact() {
        return emailContact;
    }

    public String getNameContact() {
        return nameContact;
    }

    public String getTitle() {
        return title;
    }
}

