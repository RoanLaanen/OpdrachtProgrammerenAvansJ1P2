package com.codecademy;

import static org.junit.jupiter.api.Assertions.*;

import com.codecademy.models.Constraints;
import org.junit.jupiter.api.Test;

/**
 * The ConstraintsTest class is used to test the functionality of the Constraints class.
 * It includes tests for checking the validity of an email address, zip code, percentage value and date of birth.
 */
public class ConstraintsTest {

    @Test
    public void testCheckEmailValid() {
        Constraints constraints = new Constraints();
        assertTrue(constraints.checkEmail("test@example.com"));
    }
    @Test
    public void testCheckEmailInvalidMissingAt() {
        Constraints constraints = new Constraints();
        assertFalse(constraints.checkEmail("testexample.com"));
    }
    @Test
    public void testCheckEmailInvalidMissingDomain() {
        Constraints constraints = new Constraints();
        assertFalse(constraints.checkEmail("test@"));
    }


    // This class tests the checkZip method of the Constraints class.
    // The method takes a zip code as a string and checks whether it fits a specific regular expression format.
    // The zip format is assumed to be "^(?i)[1-9][0-9]{3}\s?(?!sa|sa|ss)[a-z]{2}$".
    // Tests will verify that valid zip codes pass and invalid zip codes fail.
    @Test
    public void testCheckZipValid() {
        // Arrange
        Constraints constraints = new Constraints();
        String zip = "1234 AB";

        // Act
        boolean result = constraints.checkZip(zip);

        // Assert
        assertTrue(result, "The zip does not pass the regex although it is correct");
    }
    @Test
    public void testCheckZipInvalid() {
        // Arrange
        Constraints constraints = new Constraints();
        String zip = "abcd12";

        // Act
        boolean result = constraints.checkZip(zip);

        // Assert
        assertFalse(result, "The zip passes the regex although it is incorrect");
    }
    @Test
    public void testCheckZipEmpty() {
        // Arrange
        Constraints constraints = new Constraints();
        String zip = "";

        // Act
        boolean result = constraints.checkZip(zip);

        // Assert
        assertFalse(result, "The empty zip passes the regex");
    }
    @Test
    public void testCheckZipNull() {
        // Arrange
        Constraints constraints = new Constraints();
        String zip = null;

        // Act
        boolean result = constraints.checkZip(zip);

        // Assert
        assertFalse(result, "The null zip passes the regex");
    }


    // This class tests the checkPercentage method of the Constraints class.
    // The method takes a percentage number and checks whether it is within, above or below the range specified in the constraint
    // The range is 0-100
    // Tests will verify that valid percentages pass and invalid percentages fail
    @Test
    void testPercentageWithinRange() {
        Constraints constraints = new Constraints();
        assertTrue(constraints.checkPercentage(50));
    }
    @Test
    void testPercentageAboveRange() {
        Constraints constraints = new Constraints();
        assertFalse(constraints.checkPercentage(120));
    }

    @Test
    void testPercentageBelowRange() {
        Constraints constraints = new Constraints();
        assertFalse(constraints.checkPercentage(-1));
    }


    /*
     * ConstraintsUnitTest tests the 'checkDateOfBirth' method in the Constraints class.
     * The 'checkDateOfBirth' method validates if the given date of birth follows the correct date format defined by the regex
     */
    @Test
    void testCheckDateOfBirthValid() {
        Constraints constraints = new Constraints();
        assertTrue(constraints.checkDateOfBirth("2022-09-01"));
    }
    @Test
    void testCheckDateOfBirthInvalidNonNumeric() {
        Constraints constraints = new Constraints();
        assertFalse(constraints.checkDateOfBirth("abcd-ef-gh"));
    }
    @Test
    void testCheckDateOfBirthInvalidFormat() {
        Constraints constraints = new Constraints();
        assertFalse(constraints.checkDateOfBirth("09-01-2023"));
    }
    @Test
    void testCheckDateOfBirthNull() {
        Constraints constraints = new Constraints();
        assertFalse(constraints.checkDateOfBirth(null));
    }

}