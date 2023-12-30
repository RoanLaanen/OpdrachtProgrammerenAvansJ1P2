package com.codecademy.models;

public class Course {
    private String courseName;
    private String topic;
    private String introText;

    private levelEnum level;

    public Course(String courseName, String topic, String introText, levelEnum level) {
        this.courseName = courseName;
        this.topic = topic;
        this.introText = introText;
        this.level = level;
    }
    public enum levelEnum {
        Beginner, Gevorderd, Expert, None
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
    public void setNiveau(levelEnum level) {
        this.level = level;
    }

}
