package de.sbs.fswi1.models;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Student extends StudentDTO {

    private Timestamp zeitstempel;

    public Student(
        @JsonProperty("vorname") String vorname,
        @JsonProperty("nachname") String nachname,
        @JsonProperty("geburtsdatum") @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy") String geburtsdatum,
        @JsonProperty("klasse") String klasse) {
        super(vorname, nachname, geburtsdatum, klasse);
        zeitstempel = new Timestamp(System.nanoTime());
    }

    public long getZeitstempel() {
        return zeitstempel.getTime();
    }

    public String getNachnameVorname() {
        return String.format("%s, %s", nachname, vorname);
    }

    public int getAlterInJahrenZuHeute() {
        // Das Format des Datums festlegen
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        // String in ein LocalDate-Objekt umwandeln
        LocalDate bufGeburtsdatum = LocalDate.parse(geburtsdatum, formatter);
        // Das aktuelle Datum
        LocalDate heute = LocalDate.now();
        // Das berechnete Alter zurückgeben
        return Period.between(bufGeburtsdatum, heute).getYears();
    }

    @Override
    public boolean equals(Object other) {

        if (other == null) {
            return false;
        }

        if (other == this) {
            return true;
        }

        if (other.getClass() != this.getClass()) {
            return false;
        }

        Student otherStudent = (Student) other;

        if (otherStudent.getVorname().equals(this.getVorname())
                && otherStudent.getNachname().equals(this.getNachname())
                && otherStudent.getGeburtsdatum().equals(this.getGeburtsdatum())
                && otherStudent.getKlasse().equals(this.getKlasse())) {
            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(vorname, nachname, geburtsdatum, klasse);
    }
}