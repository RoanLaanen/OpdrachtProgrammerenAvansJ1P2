package com.codecademy.models;

public class User {
    private String name;
    private String dateOfBirth;
    private String gender;
    private String email;
    private String zip;
    private String address;
    private String country;
    public User(String name, String email, String dateOfBirth, String gender, String address, String zip,String country){
        this.name = name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
        this.zip = zip;
        this.country = country;
    }



    public String getName() {
        return name;
    }
    public String getDateOfBirth() {
        return dateOfBirth;
    }
    public String getGender() {
        if (gender.equals("0")) {
            return "Female";
        }
        return "Male";
    }

    public String getGenderBit() { return gender; }
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
