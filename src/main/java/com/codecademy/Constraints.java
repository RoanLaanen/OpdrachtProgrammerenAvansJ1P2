package com.codecademy;

public class Constraints {
    public boolean checkEmail(String email){
        String regex = "^.@...*$";
        return email.matches(regex);
       
    }
    public boolean checkZip(String date){
        String regex = "^[1-9]\\d{3} [A-Z]{2}$";
        return date.matches(regex);

    }
    public boolean checkPercentage(int perc){
        return(perc<100 && perc>0);
    }
    public boolean checkDateOfBirth(String dob){
        String regex = "^\\d{2}-\\d{2}-\\d{4}$";
        return dob.matches(regex);
    }

}
