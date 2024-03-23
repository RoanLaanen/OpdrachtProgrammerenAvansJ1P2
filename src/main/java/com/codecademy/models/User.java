package com.codecademy.models;

import java.sql.Date;
import java.time.LocalDate;

public class User {
    private String name;
    private LocalDate dateOfBirth;
    private String gender;
    private String email;
    private String zip;
    private String address;
    private String country;
    public User(String name, String email, LocalDate dateOfBirth, String gender, String address, String zip,String country){
        this.name = name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
        this.zip = zip;
        this.country = country;
    }

    public User() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public String getGender() {
        return gender;
    }
    public String getEmail() {
        return email.toLowerCase();
    }

    public String getAddress() {
        return address;
    }

    public String getCountry() {
        return country;
    }

    public String getZip() {
        return zip;
    }
}
