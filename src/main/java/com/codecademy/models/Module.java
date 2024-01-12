package com.codecademy.models;

import java.time.LocalDate;

public class Module extends ContentItem {
    private String title;
    private float version;
    private String description;
    private String emailContact;
    private String nameContact;

    private String courseName;
    public Module(Status status, LocalDate publishingDate,String title, float version, String description, String emailContact, String nameContact, int contentID, String courseName){
        super(contentID,status,publishingDate);
        this.title = title;
        this.version = version;
        this.description = description;
        this.emailContact = emailContact;
        this.nameContact = nameContact;
        this.courseName = courseName;
    }

    public String getCourseName() {
        return courseName;
    }

    public float getVersion() {
        return version;
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

