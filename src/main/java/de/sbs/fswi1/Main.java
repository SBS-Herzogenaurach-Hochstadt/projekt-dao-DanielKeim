package de.sbs.fswi1;

import de.sbs.fswi1.models.Student;

public class Main {
    public static void main(String[] args) {
        // Pfad muss angepasst werden
        /*
        DataAccessObject dao = new DataAccessObject("C:\\Users\\cgg\\Documents\\data\\studenten.csv");
        List<StudentDTO> studenten = dao.findAll();

        for (StudentDTO studentDTO : studenten) {
            System.out.println(studentDTO);
        }
        */
        Student std1 = new Student("Peter", "Lustig", "12.12.2000", "FSWI-1");
        System.out.println(std1.getAlterInJahrenZuHeute());
    }
}