package com.codecademy;

public class Constraints {

    // Method to verify if the given email is valid according to the regular expression
    public boolean checkEmail(String email){
        String regex = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        if (email != null) {
            return email.matches(regex);
        }
        return false;
    }

    // Method to verify if the given Zip code follows the correct format defined by the regex
    public boolean checkZip(String zip){
        String regex = "^(?i)[1-9][0-9]{3}\\s?(?!sa|sa|ss)[a-z]{2}$";
        if (zip != null) {
            return zip.matches(regex);
        }
        return false;
    }

    // Method to check if given percentage value is between 0 and 100
    public boolean checkPercentage(int perc){
        return(perc<100 && perc>0);
    }

    // Method to validate if the given date of birth follows the correct date format defined by the regex
    public boolean checkDateOfBirth(String dob){
        String regex = "^\\d{4}-\\d{2}-\\d{2}$";
        if (dob != null) {
            return dob.matches(regex);
        }
        return false;
    }
}
