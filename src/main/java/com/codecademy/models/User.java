package com.codecademy.models;

public class User {
    private String name;
    private String dateOfBirth;
    private int gender;
    private String email;
    private String residence;
    private String address;
    private String country;
    public User(String name,String dateOfBirth, int gender,String email,String residence,String address,String country){
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.email = email;
        this.residence = residence;
        this.address = address;
        this.country = country;
    }
    public String getName() {
        return name;
    }
    public String getDateOfBirth() {
        return dateOfBirth;
    }
    public int getGender() {
        return gender;
    }
    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getCountry() {
        return country;
    }

    public String getResidence() {
        return residence;
    }
}
