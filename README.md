# Project Overview
The project is a Java-based application for managing and manipulating Course data. The primary function is to allow users to interact with a MySQL database through a GUI to add, delete, update, and view Course records.
This application is built using the following structure:


# ToDo
feedback/opmerkingen:
- Na het toevoegen krijg ik een foutmelding "Caused by: java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0" op regel 30 van GUI.java. Ik vermoed dat er geen data in de database zit.
- DatabaseConnection heeft alleen static methodes. Idealiter wil je een state bijhouden: bijvoorbeeld een open connectie waarop je binnen methodes queries uit kunt voeren.
- Coderen in het Engels. Zelfde geld voor eventuele comments, ook in het Engels.
- Er zullen veel entiteiten bij komen, ook veel GUI en Database code daardoor. Denk alvast na over de toekomst: hoe zou je dit een beetje kunnen structureren om te voorkomen dat de klassen ellelang en groot worden?