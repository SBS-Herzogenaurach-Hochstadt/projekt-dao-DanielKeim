package de.sbs.fswi1;

import java.util.List;

import de.sbs.fswi1.models.Student;
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
        
        Student std1 = new Student("Peter", "Lustig", "12.12.2000", "FSWI-1");
        Student std2 = new Student("Peter", "Lustig", "12.12.2000", "FSWI-2");
        StudentDTO std3 = new StudentDTO("Peter", "Lustig", "12.12.2000", "FSWI-1");
        StudentDTO std4 = new StudentDTO("Peter", "Lustiger", "12.12.2000", "FSWI-1");

        // Aufruf der save-Methode, um die Werte des Studenten std4 in die Studenten.csv zu speichern 
        dao.save(std4);

        System.out.println(std1.hashCode());
        System.out.println(std2.hashCode());
        System.out.println(std3.hashCode());
        System.out.println(std4.hashCode());
        System.out.println(std1.equals(std2));
    }
}