package de.sbs.fswi1;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import de.sbs.fswi1.models.StudentDTO;
import de.sbs.fswi1.services.DataAccessObject;

public class Main {
    public static void main(String[] args) {
        // Pfad muss angepasst werden
        DataAccessObject dao = new DataAccessObject("C:\\Users\\cgg\\Documents\\data\\studenten.csv");
        List<StudentDTO> studenten = dao.findAll();

        for (StudentDTO studentDTO : studenten) {
            System.out.println(studentDTO);
        }
    }
}