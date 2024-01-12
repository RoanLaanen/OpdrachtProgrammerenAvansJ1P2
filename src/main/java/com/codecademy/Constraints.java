package com.codecademy;

public class Constraints {

    // Method to verify if the given email is valid according to the regular expression
    public boolean checkEmail(String email){
        String regex = "^.@..+$";
        return email.matches(regex);
    }

    // Method to verify if the given Zip code follows the correct format defined by the regex
    public boolean checkZip(String date){
        String regex = "^[1-9]\\d{3} [A-Z]{2}$";
        return date.matches(regex);
    }

    // Method to check if given percentage value is between 0 and 100
    public boolean checkPercentage(int perc){
        return(perc<100 && perc>0);
    }

    // Method to validate if the given date of birth follows the correct date format defined by the regex
    public boolean checkDateOfBirth(String dob){
        String regex = "^\\d{2}-\\d{2}-\\d{4}$";
        return dob.matches(regex);
    }
}
