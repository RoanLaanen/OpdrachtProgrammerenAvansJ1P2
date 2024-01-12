package com.codecademy.models;

import java.time.LocalDate;

public class Webcast extends ContentItem{
    private String title;
    private String description;
    private String nameLecturer;
    private String organisationLecturer;
    private String url;
    private int duration;

    public Webcast(Status status, LocalDate publishingDate, String title, int duration, String description, String nameLecturer, String organisationLecturer, int contentID, String url){
        super(contentID,status,publishingDate);
        this.title = title;
        this.duration = duration;
        this.description = description;
        this.nameLecturer = nameLecturer;
        this.organisationLecturer = organisationLecturer;

        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
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
