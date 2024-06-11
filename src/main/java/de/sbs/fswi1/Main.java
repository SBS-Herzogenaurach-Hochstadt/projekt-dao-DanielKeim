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

    static DataAccessObject dao;

    public static void main(String[] args) {

        // Pfad muss angepasst werden
        // dao = new DataAccessObject("C:\\Users\\cgg\\Documents\\data\\studenten.csv");

        Path pfadAufCSV = Path.of("C:/Users/cgg/Documents/data/studenten.csv");
        try {
            String csvContent = Files.readString(pfadAufCSV);

            if (csvContent != null) {
                createJson(csvContent);
            }

        } catch (Exception ignored) {
        }

        /*
         * List<StudentDTO> studenten = dao.findAll();
         * 
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
    }

    private static void createJson(String csvContent) {
        String[] lines = csvContent.split("\n");

        StringBuilder json = new StringBuilder("[");

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