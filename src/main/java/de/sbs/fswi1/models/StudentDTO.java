package de.sbs.fswi1.models;

<<<<<<< HEAD
public class StudentDTO extends Mensch {

=======
import java.util.Objects;

public class StudentDTO extends Mensch {
>>>>>>> 0d2a51e (save code)
    protected String klasse;

    public StudentDTO(String vorname, String nachname, String geburtsdatum, String klasse) {
        super(vorname, nachname, geburtsdatum);
        this.klasse = klasse;
    }

    public String getKlasse() {
        return klasse;
    }
<<<<<<< HEAD
=======

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
>>>>>>> 0d2a51e (save code)
}
