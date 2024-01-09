package com.codecademy.models;

public class Webcast {
    private String title;
    private String description;
    private String nameLecturer;
    private String organisationLecturer;
    private String url;
    private int duration;
    private final int contentID;
    public Webcast(String title,int duration, String description,String nameLecturer,String organisationLecturer,int contentID,String url){
        this.title = title;
        this.duration = duration;
        this.description = description;
        this.nameLecturer = nameLecturer;
        this.organisationLecturer = organisationLecturer;
        this.contentID = contentID;
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public int getContentID() {
        return contentID;
    }

    public int getDuration() {
        return duration;
    }

    public String getNameLecturer() {
        return nameLecturer;
    }

    public String getOrganisationLecturer() {
        return organisationLecturer;
    }

    public String getUrl() {
        return url;
    }
}
