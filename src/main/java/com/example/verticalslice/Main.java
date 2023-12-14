package com.example.verticalslice;


public class Main {
    public static String introcTekst;

    public static void main(String[] args) {
        // Create an instance of DatabaseConnection
        DatabaseConnection dbConnection = new DatabaseConnection();

        // Call a method to initialize the database connection and retrieve data
        dbConnection.initializeDatabaseConnection();
        Object introcTekst = null;
        // Now, you can access the value of introductieTekst
        introcTekst = DatabaseConnection.introductieTekst;

        System.out.println("Hello World!");

        System.out.println(introcTekst);

        // Rest of your code...
    }


    public static Cursus cursus1 = new Cursus("AngularJS Frontend Framework", "Programmeren", introcTekst, Cursus.niveau.Expert);
//    public static Cursus cursus1 = new Cursus("AngularJS Frontend Framework", "Programmeren", "Welkom bij de Codecademy-cursus \"AngularJS Frontend Framework\"! Ontdek de kracht van Google's AngularJS en leer stap voor stap hoe je dynamische gebruikersinterfaces bouwt. Of je nu een beginner bent of een ervaren ontwikkelaar, deze cursus biedt een gestructureerde en hands-on aanpak. Duik in de wereld van AngularJS en ontdek hoe dit framework de webontwikkeling transformeert. Klaar om indrukwekkende frontend-applicaties te bouwen? Laten we beginnen!", Cursus.niveau.Expert);
}
