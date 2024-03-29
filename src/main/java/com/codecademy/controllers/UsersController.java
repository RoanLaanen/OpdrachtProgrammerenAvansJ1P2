package com.codecademy.controllers;

import com.codecademy.database.UserDao;
import com.codecademy.models.Constraints;
import com.codecademy.models.DataSingleton;
import com.codecademy.database.DatabaseConnection;
import com.codecademy.models.Certificate;
import com.codecademy.models.Course;
import com.codecademy.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;


public class UsersController implements Initializable {
    @FXML
    public ListView<String> userList;


    Constraints constraints = new Constraints();
    @FXML
    public ListView<String> certificateList;
    @FXML
    public ListView<String> courseList;
    String selectedUser;
    String selectedCourse;
    public TextField nameField;
    public TextField emailField;
    public ComboBox<String> genderField;
    public DatePicker dateOfBirthPicker;
    public TextField addressField;
    public TextField zipField;
    public TextField countryField;

    DataSingleton data = DataSingleton.getInstance();
    private ArrayList<Certificate> certificates;
    private ArrayList<Course> courses;
    HashMap<String, String> userNames = new HashMap<>();
    HashMap<String, String> userEmails = new HashMap<>();
    HashMap<String, LocalDate> userDateOfBirths = new HashMap<>();
    HashMap<String, String> userGenders = new HashMap<>();
    HashMap<String, String> userAddresses = new HashMap<>();
    HashMap<String, String> userZips = new HashMap<>();
    HashMap<String, String> userCountries = new HashMap<>();

    private void extractUserData() {
        ArrayList<User> users = UserDao.getAllUsers();
        userNames.clear();
        userEmails.clear();
        userDateOfBirths.clear();
        userGenders.clear();
        userAddresses.clear();
        userZips.clear();
        userCountries.clear();

        for (User user : users) {
            String email = user.getEmail();
            userNames.put(email, user.getName());
            userEmails.put(email, user.getEmail());
            userDateOfBirths.put(email, user.getDateOfBirth());
            userGenders.put(email, user.getGender());
            userAddresses.put(email, user.getAddress());
            userZips.put(email, user.getZip());
            userCountries.put(email, user.getCountry());
        }
        Collection<String> values = userEmails.values();

        ArrayList<String> listOfUserValues = new ArrayList<>(values);
        ObservableList<String> items = FXCollections.observableArrayList((listOfUserValues));
        userList.setItems(items);

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        extractUserData();
        genderField.getItems().setAll("Male", "Female");
        userList.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
                    selectedUser = userList.getSelectionModel().getSelectedItem();
            System.out.println(selectedUser);
                    nameField.setText(userNames.get(selectedUser));
                    emailField.setText(selectedUser);
                    dateOfBirthPicker.setValue(userDateOfBirths.get(selectedUser));
                    genderField.getSelectionModel().select(userGenders.get(selectedUser));
                    addressField.setText(userAddresses.get(selectedUser));
                    zipField.setText(userZips.get(selectedUser));
                    countryField.setText(userCountries.get(selectedUser));
                    certificates = DatabaseConnection.getCertificatesForUser(selectedUser);

                    courses = DatabaseConnection.getCoursesForUser(selectedUser);

            ArrayList<String> certificateValues = new ArrayList<>();
            if (!certificates.isEmpty()) {
                for (Certificate certificate : certificates) {
                    certificateValues.add(certificate.getCourseName());
                }
            }

            ArrayList<String> listOfCertificateValues = new ArrayList<>(certificateValues);
            ObservableList<String> Certificate = FXCollections.observableArrayList((listOfCertificateValues));
            certificateList.setItems(Certificate);
                    ArrayList<String> courseValues = new ArrayList<>();
            if (!courses.isEmpty()) {
                for (Course course : courses) {
                    courseValues.add(course.getCourseName());
                }
            }

            ArrayList<String> listOfCourseValues = new ArrayList<>(courseValues);
            ObservableList<String> Course = FXCollections.observableArrayList((listOfCourseValues));
            courseList.setItems(Course);
        });
        courseList.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            try {
                 selectedUser = userList.getSelectionModel().getSelectedItem();
                 selectedCourse = courseList.getSelectionModel().getSelectedItem();
                changeSceneToUserCourses(courseList);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }

    public ObservableList<String> getListNames(List<?> items) {
        ArrayList<String> values = new ArrayList<>();
        for (Object item : items) {
            values.add(item.toString()); // Assuming toString() returns the name of the item
            System.out.println(item);
        }
        return FXCollections.observableArrayList(values);
    }


    public void changeSceneToUserCourses(Node node) throws IOException {
        data.setSelectedUser(selectedUser);
        data.setSelectedCourse(selectedCourse);

        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/userCourses.fxml")));
        Scene scene = new Scene(parent);

        Stage window = (Stage) node.getScene().getWindow();
        window.setUserData(data);
        window.setScene(scene);
        window.show();
    }


    public void changeSceneToMain(MouseEvent mouseEvent) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/MainWindow.fxml")));
        Scene scene = new Scene(parent);

        Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void deleteUser() {
        UserDao.deleteUser(selectedUser);
        extractUserData();
        selectedUser = null;
    }

    public void clearFields() {
        nameField.clear();
        nameField.setPromptText("Name");
        emailField.clear();
        emailField.setPromptText("Email");
        dateOfBirthPicker.setPromptText("Date of Birth");
        genderField.setPromptText("Gender");
        addressField.clear();
        addressField.setPromptText("Address");
        zipField.clear();
        zipField.setPromptText("Zip-code");
        countryField.clear();
        countryField.setPromptText("Country");
    }

    public void addUser() {
        selectedUser = null;
        clearFields();
        userList.getSelectionModel().clearSelection();
    }

    public void saveUser() {

        User user = new User();
        String name = nameField.getText();
        String email = emailField.getText();
        LocalDate dob = dateOfBirthPicker.getValue();
        String gender = genderField.getSelectionModel().getSelectedItem();
        String address = addressField.getText();
        String zip = zipField.getText();
        String country = countryField.getText();
        if(!checkAddUserFields(email,zip, String.valueOf(dob))){
            System.out.println("incorrect fields");
        }
        else {
            user.setAddress(address);
            user.setName(name);
            user.setEmail(email);
            user.setGender(gender);
            user.setCountry(country);
            user.setZip(zip);
            user.setDateOfBirth(dob);
            if (selectedUser == null) {
                UserDao.addUser(user);

            } else {
                UserDao.updateUser(selectedUser, user);
            }
            extractUserData();
            selectedUser = user.getEmail();
            userList.getSelectionModel().select(selectedUser);

        }
    }
    public boolean checkAddUserFields(String email, String zip, String dateOfBirth){
        if(constraints.checkEmail(email) && constraints.checkDateOfBirth(dateOfBirth) && constraints.checkZip(zip)){
            System.out.println("fields are all correct");
            return true;
        }
        else {
            System.out.println("Email: " + constraints.checkEmail(email));
            System.out.println("dateOfBirth: " + constraints.checkDateOfBirth(String.valueOf(dateOfBirth)) + " " +  dateOfBirth);
            System.out.println("zip: " + constraints.checkZip(zip));
            return false;
        }

    }
}
