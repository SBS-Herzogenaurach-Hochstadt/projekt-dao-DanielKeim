package de.sbs.fswi1.models;

public abstract class Mensch {
    protected String vorname, nachname;
    protected final String geburtsdatum;

    public Mensch(String vorname, String nachname, String geburtsdatum) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.geburtsdatum = geburtsdatum;
    }

    public String getVorname() {
        return vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public String getGeburtsdatum() {
        return geburtsdatum;
    }
}