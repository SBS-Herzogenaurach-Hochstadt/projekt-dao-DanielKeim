package de.sbs.fswi1.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import de.sbs.fswi1.models.StudentDTO;

public class DataAccessObject {

    private final String pathAsString;

    public DataAccessObject(String pathAsString) {
        this.pathAsString = pathAsString;
    }

    public List<StudentDTO> findAll() {
        // Zugriff auf eine gespeicherte Datei benötigt IMMER einen PATH
        Path pfadAufCSV = Path.of(pathAsString);

        // Wir machen nur etwas, wenn die Datei wirklich existiert
        if (Files.exists(pfadAufCSV)) {

            // FehlerBEHANDLUNG, weil die Methode readString mehrere Exceptions wirft, wenn etwas nicht klappt
            // Bei Zugriff auf das Dateiensystem fordert Java try - catch
            try {
                // ALLES als Zeichenfolge einlesen
                String csvContent = Files.readString(pfadAufCSV);

                // Zeichenfolge am Zeilenumbruchzeichen "\n" zerlegen und als Array zurückgeben
                String[] lines = csvContent.split("\n");

                // leere List instanziieren, um in und nach if Zugriff darauf zu haben
                List<StudentDTO> studenten = new ArrayList<>();

                // Mittels for-schleife über alle Datenzeilen gehen
                for (String line : lines) {
                    // jeweils die aktuelle Datenzeile am Komma zerlegen um ein Array mit den einzelnen Elementen zu bekommen
                    String[] elements = line.split(",");

                    // Erzeugung einer Instanz der Klasse Student mit den gespeicherten Werte aus
                    // dem Array elements, das mit der .split(",") erzeugt wurde
                    StudentDTO lokStudent = new StudentDTO(elements[0].trim(), elements[1].trim(), elements[2].trim(), elements[3].trim());

                    // Aufnaheme des Objekts Student (lokStudent) in die Liste
                    studenten.add(lokStudent);

                    // Alternative
                    // studenten.add(new StudentDTO(elements[0], elements[1], elements[2], elements[3]));
                }

                return studenten;
                // ACHTUNG: Nach return endet immer ein Codebereich und die Methode springt
                // zurück zum Aufrufer

            } catch (IOException e) {

                // Ausgabe einer möglichen Fehlermeldung auf der Console
                e.printStackTrace();
            }
        }

        // Sollte alles nicht geklappt haben, dann gibt eine null-Referenz an den
        // Aufrufer zurück!
        // BEACHTE: Es kann mehrere returns gebe, ABER immer entweder oder erreichbar
        return null;
    }    
}