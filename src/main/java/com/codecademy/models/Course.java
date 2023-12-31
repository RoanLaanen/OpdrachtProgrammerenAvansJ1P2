package com.codecademy.models;

public class Course {
    private String courseName;
    private String topic;
    private String introText;

    private Level level;

    public Course(String courseName, String topic, String introText, Level level) {
        this.courseName = courseName;
        this.topic = topic;
        this.introText = introText;
        this.level = level;
    }

    public String getCourseName() {
        return courseName;
    }
    public String getLevel() {
        return level.toString();
    }
    public String getTopic() {
        return topic;
    }
    public String getIntroText() {
        return introText;
    }
    public void setNiveau(Level level) {
        this.level = level;
    }

}
