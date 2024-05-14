package de.sbs.fswi1.models;

public class StudentDTO extends Mensch {
    
    private String klasse;

    public StudentDTO(String vorname, String nachname, String geburtsdatum, String klasse) {
        super(vorname, nachname, geburtsdatum);
        this.klasse = klasse;
    }

    
    public String getKlasse() {
        return klasse;
    }

    @Override
    public String toString() {
        return "StudentDTO [vorname=" + vorname + ", nachname=" + nachname + ", klasse=" + klasse + ", geburtsdatum="
                + geburtsdatum + "]";
    }
}
