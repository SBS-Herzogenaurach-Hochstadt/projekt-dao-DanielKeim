package de.sbs.fswi1.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import de.sbs.fswi1.models.StudentDTO;

public class DataAccessObject {

    private final String pathAsString;

    public DataAccessObject(String pathAsString) {
        this.pathAsString = pathAsString;
    }

    public boolean save(StudentDTO saveObject) {
        Path pfadAufCSV = Path.of(pathAsString);
        if (Files.exists(pfadAufCSV)) {
            try {
                String content = String.format("%s,%s,%s,%s%n", saveObject.getVorname(), saveObject.getNachname(), saveObject.getGeburtsdatum(), saveObject.getKlasse());
                Files.writeString(pfadAufCSV, content, StandardOpenOption.APPEND);
                return true;
            } catch (Exception ignored) {
            }
        }
        return false;
    }

    public List<StudentDTO> findAll() {
        Path pfadAufCSV = Path.of(pathAsString);
        if (Files.exists(pfadAufCSV)) {
            try {
                String csvContent = Files.readString(pfadAufCSV);
                return getStudentDTOS(csvContent);
            } catch (Exception ignored) {
            }
        }
        return null;
    }

    private static List<StudentDTO> getStudentDTOS(String csvContent) {
        String[] lines = csvContent.split("\n");
        List<StudentDTO> studenten = new ArrayList<>();
        for (String line : lines) {
            String[] elements = line.split(",");
            studenten.add(new StudentDTO(elements[0], elements[1], elements[2],elements[3]));
        }
        return studenten;
    }
}