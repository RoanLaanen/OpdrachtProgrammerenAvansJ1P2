# Project Overview
The project is a Java-based application for managing and manipulating Course data. The primary function is to allow users to interact with a MySQL database through a GUI to add, delete, update, and view Course records.
This application is built using the following structure:

## Main.java
The Main.java file is the primary entry point of the application. It initializes the database by loading existing Course instances from the database. It then calls the GUI class to create and show the GUI.

    public class Main {
	    public static void main(String[] args) {
	        DatabaseConnection.updateCursusArray();
	        GUI.main(args);
		}
	}
## Course.java
This is a model class known as 'Course'. The Course model includes fields like courseName, topic, introText, and level.

    public class Course {
	    // attributes...
	    public Course(String courseName, String topic, String introText, levelEnum level) {
	        this.courseName = courseName;
	        this.topic = topic;
	        this.introText = introText;
	        this.level = level;
	    }
	}
## DatabaseConnection.java
The DatabaseConnection class handles all database-related operations.

    public class DatabaseConnection {
	    // Attributes and methods...
	}
## GUI.java
The application uses JavaFX to create a GUI allowing users to interact with the database. The GUI includes a ComboBox for selecting existing courses, fields for viewing and editing the details of the selected course, and buttons for manipulating the course data.


### Overview of the GUI Layout:
The GUI.java class contains various sections:

**ComboBox Section:** This section includes a ComboBox that lists all the courses available in the database.
**Details Section:** This section displays the details of the course selected in the ComboBox. It displays fields for the title, topic, level, and introductory text of the course.
**Button Section:** This section houses the "Add", "Edit", and "Delete" buttons. Clicking these buttons triggers related database operations for adding a new course, editing an existing course or deleting a course.


### Event Handling:
Event Handling in the GUI is done using JavaFX's event handling mechanism. When an event occurs on a node, such as a Button click, a corresponding method in the GUI class is executed


### Building Scene and Stage:
JavaFX handles the lifecycle of a GUI application through stages and scenes. The primary stage is created by the JavaFX platform itself while the developer is responsible for creating the scenes and switching between them.

    public class GUI extends Application {
	    // GUI Definition...
	}

## Requirements
- Java SDK
- MySQL database
- JavaFX library

## Getting Started
The entry point for this application is the Main.java class. Running this class will start up the GUI and load courses from the database.
Ensure to update the database connection URL and credentials in the DatabaseConnection class to match your MySQL database.
Please do not forget that this is a sample README.md and might not contain all the best practices for documentation. Always ensure to document your code in a way that it's clear for other developers.