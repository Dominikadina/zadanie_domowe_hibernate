package parser;

import dao.GenericDao;
import jakarta.persistence.criteria.CriteriaBuilder;
import model.Ocena;
import model.Przedmiot;
import model.Student;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class OcenaParser {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final Scanner scanner;
    private final GenericDao<Student> daoS;
    private final GenericDao<Ocena> daoO;

    public OcenaParser(Scanner scanner, GenericDao<Student> daoS, GenericDao<Ocena> daoO) {
        this.scanner = scanner;
        this.daoS = daoS;
        this.daoO = daoO;
    }


    public void wykonaj() {
        String komenda;
        do {
            System.out.println("Komenda: [addS, findS, listS, deleteS, updateS]");
            komenda = scanner.next();
            if (komenda.equalsIgnoreCase("addS")) {
                handleAddCommand();
            } else if (komenda.equalsIgnoreCase("listS")) {
                handleListCommand();
            } else if (komenda.equalsIgnoreCase("deleteS")) {
                handleDeleteCommand();
            } else if (komenda.equalsIgnoreCase("updateS")) {
                handleUpdateCommand();
            }
        } while (!komenda.equals("wyjdz"));
    }

    private void handleAddCommand() {
        System.out.println("Provide students name");
        String name = scanner.next();

        System.out.println("Provide students lastname");
        String last_name = scanner.next();

        System.out.println("Provide students indeks number");
        String indeks_no = scanner.next();

        System.out.println("Provide students birthdate");
        String birthdate = scanner.next();

        System.out.println("Provide mark");
        Integer listaOcen = scanner.nextInt();

        Student student = new Student(name, last_name, indeks_no, birthdate, listaOcen);
        daoS.dodaj(student);
    }

    private void handleListCommand() {
        List<Student> studentLista = daoS.list(Student.class);
        for (Student student : studentLista) {
            System.out.println(student);
        }
        System.out.println();
    }

    private void handleUpdateCommand() {
        System.out.println("Provide Students id to update:");
        Long id = scanner.nextLong();

        Optional<Student> studentOptional = daoS.znajdzPoId(id, Student.class);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();

            System.out.println("What do you want to update: name or last_name");
            String output = scanner.next();
            switch (output) {
                case "name":
                    System.out.println("Provide new name:");
                    String name = scanner.next();
                    student.setName(name);
                    break;

                case "last_name":
                    System.out.println("Provide new last_name:");
                    String last_name = scanner.next();
                    student.setLast_name(last_name);
                    break;

                default:
                    System.out.println("Field with this name and last_name is not handled.");
            }

            daoS.aktualizuj(student);
            System.out.println("Students parameters were updated.");
        } else {

            System.out.println("No students founded");
        }
    }

    private void handleDeleteCommand() {
        System.out.println("Provide id of the student");
        Long id = scanner.nextLong();

        Optional<Student> studentOptional = daoS.znajdzPoId(id, Student.class);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            daoS.usun(student);
            System.out.println("Student was deleted from students list");
        } else {
            System.out.println("No students founded");
        }
    }
}


//    private LocalDateTime zaladujDateICzasOtrzymaniaOceny() {
//        LocalDateTime dataICzasOtrzymaniaOceny = LocalDateTime.now();
//        System.out.println("Date and time of adding the grade:" + dataICzasOtrzymaniaOceny);
//        return dataICzasOtrzymaniaOceny;
//    }
//
//        private Przedmiot zaladujPrzedmiot () {
//            Przedmiot przedmiot = null;
//            do {
//                try {
//                    System.out.println("Provied class name:");
//                    String unitString = scanner.next();
//
//                    przedmiot = Przedmiot.valueOf(unitString.toUpperCase());
//                } catch (IllegalArgumentException iae) {
//                    System.err.println("No such subject");
//                }
//            } while (przedmiot == null);
//            return przedmiot;
//        }
//    }
//}

