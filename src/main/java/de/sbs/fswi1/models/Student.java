package de.sbs.fswi1.models;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Student extends StudentDTO {

    public Student(String vorname, String nachname, String geburtsdatum, String klasse) {
        super(vorname, nachname, geburtsdatum, klasse);
    }

     public String getNachnameVorname() {
        return String.format("%s, %s", nachname, vorname);
    }

    public int getAlterInJahren() {
     
        // Das Format des Datums festlegen
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        // String in ein LocalDate-Objekt umwandeln
        LocalDate bufGeburtsadatum = LocalDate.parse(geburtsdatum, formatter);

        // Das aktuelle Datum
        LocalDate heute = LocalDate.now();

        // Das Alter berechnen
        int alter = Period.between(bufGeburtsadatum, heute).getYears();

        return alter;
    }
}
