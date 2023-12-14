package com.example.verticalslice;

public class Cursus {
    private String cursusNaam;
    private String onderwerp;
    private String introductieTekst;

    private niveau niveau;

    public Cursus(String cursusNaam, String onderwerp, String introductieTekst, niveau niveau) {
        this.cursusNaam = cursusNaam;
        this.onderwerp = onderwerp;
        this.introductieTekst = introductieTekst;
        this.niveau = niveau;
    }
    public enum niveau {
        Beginner, Gevorderd, Expert
    }

    public String getCursusNaam() {
        return cursusNaam;
    }
    public String getNiveau() {
        return niveau.toString();
    }
    public String getOnderwerp() {
        return onderwerp;
    }
    public String getIntroductieTekst() {
        return introductieTekst;
    }
    public void setNiveau(niveau niveau) {
        this.niveau = niveau;
    }
}
