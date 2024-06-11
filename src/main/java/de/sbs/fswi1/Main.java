package de.sbs.fswi1;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.sbs.fswi1.models.Student;
import de.sbs.fswi1.models.StudentDTO;
import de.sbs.fswi1.services.DataAccessObject;

@SuppressWarnings("unused")
public class Main {
    // Klassen-Scope

    public static void main(String[] args) {
        // main-Methoden-Scope

        Path pfadAufCSV = Path.of("C:/Users/cgg/Documents/data/studenten.csv");
        try {
            String csvContent = Files.readString(pfadAufCSV);
            if (csvContent != null) {
                createJson(csvContent);
            }
        } catch (Exception ignored) {
        }

        createJsonAusListe();
    }
    /*
     * List<StudentDTO> studenten = dao.findAll();
     * Pfad muss angepasst werden
     * dao = new DataAccessObject("C:\\Users\\cgg\\Documents\\data\\studenten.csv");
     * for (StudentDTO studentDTO : studenten) {
     * System.out.println(studentDTO);
     * }
     * 
     * Student std1 = new Student("Peter", "Lustig", "12.12.2000", "FSWI-1");
     * Student std2 = new Student("Peter", "Lustig", "12.12.2000", "FSWI-1");
     * 
     * System.out.println(std2.hashCode());
     * System.out.println(((Object)std2).hashCode());
     */

    private static void createJsonAusListe() {

        // im Kopf Schritte bestimmen, die nötig sind!!!!!!
        // Schritt 1: DAO erzeugen
        DataAccessObject dao = new DataAccessObject("C:/Users/cgg/Documents/data/studenten.csv");

        // Schrtitt 2: dao bnutzen, um List<Student>
        List<StudentDTO> liste = dao.findAll();

        // Schritt 3
        // new StringBuilder-Objekt vorbelegt mit "["
        StringBuilder json = new StringBuilder("[");

        // Schritt 4
        // Festlegung "Key":"Value" mit Platzhalter %s für das spätere Value
        String format = "{\"vorname\":\"%s\",\"nachname\":\"%s\",\"geburtsdatum\":\"%s\",\"klasse\":\"%s\"}";
        
        // Schritt 5 Schleife 
        for (int i = 0; i < liste.size(); i++) {
            // Zugriff auf die StudentenDTO-Objekte der Liste über den Index der Schleife         
            json.append(String.format(format, liste.get(i).getVorname(),
            liste.get(i).getNachname(),
            liste.get(i).getGeburtsdatum(),
            liste.get(i).getKlasse()));
            
            if (i < liste.size() - 1) {
                json.append(",");
            }
        }
        json.append("]");

        try {
            Path pfadAufJson = Path.of("C:/Users/cgg/Documents/data/studentenAusListe.json");
            Files.writeString(pfadAufJson, json.toString());
        } catch (Exception ignored) {
        }
    }

    private static void createJson(String csvContent) {
        String[] lines = csvContent.split("\n");

        // new StringBuilder-Objekt vorbelegt mit "["
        StringBuilder json = new StringBuilder("[");

        // Festlegung "Key":"Value" mit Platzhalter %s für das spätere Value
        String format = "{\"vorname\":\"%s\",\"nachname\":\"%s\",\"geburtsdatum\":\"%s\",\"klasse\":\"%s\"}";

        for (int i = 0; i < lines.length; i++) {
            String[] items = lines[i].split(",");
            if (items.length == 4) {
                json.append(String.format(format, items[0], items[1], items[2], items[3].trim()));
            }
            if (i < lines.length - 1) {
                json.append(",");
            }
        }
        json.append("]");

        try {
            Path pfadAufJson = Path.of("C:/Users/cgg/Documents/data/studenten.json");
            Files.writeString(pfadAufJson, json.toString());
        } catch (Exception ignored) {
        }

        ObjectMapper mapper = new ObjectMapper();

        try {
            List<Student> studentenAusJson = mapper.readValue(json.toString(), new TypeReference<List<Student>>() {
            });

            // Print the mapped students
            for (Student student : studentenAusJson) {
                System.out.println(student.getZeitstempel());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*
         * try {
         * String json2 = mapper.writeValueAsString(dao.findAll());
         * System.out.println(json2);
         * } catch (Exception e) {
         * e.printStackTrace();
         * }
         */

    }
}