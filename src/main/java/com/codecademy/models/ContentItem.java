package com.codecademy.models;

import java.time.LocalDate;

public abstract class ContentItem {
    private int contentID;
    private Status status;
    private LocalDate publishingDate;


    protected ContentItem(int contentID, Status status, LocalDate publishingDate) {
        this.contentID = contentID;
        this.status = status;
        this.publishingDate = publishingDate;
    }




}
