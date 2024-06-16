package tracker;

import tracker.exceptions.DuplicateEmailException;

import java.util.Scanner;

public class AddStudentsCommand implements Command {
    private final Scanner scanner;
    private final StudentDAO dao;

    public AddStudentsCommand(Scanner scanner) {
        this.scanner = scanner;
        dao = InMemoryStudentDAO.dao();
    }

    @Override
    public boolean execute() {
        System.out.println("Enter student credentials or 'back' to return:");

        String input;
        do {
            input = scanner.nextLine();
            if (!input.equalsIgnoreCase("back")) {
                StudentValidator validator = Student.getValidator();
                if (validator.isValid(input)) {
                    Student student = Student.fromString(input);
                    try {
                        dao.save(student);
                    } catch (DuplicateEmailException e) {
                        System.out.println(e.getMessage());
                        continue;
                    }
                    System.out.println("The student has been added.");
                } else {
                    System.out.println(validator.getMessage());
                }
            }
        } while (!input.equalsIgnoreCase("back"));

        System.out.printf("Total %d students have been added.%n", dao.size());
        return false;
    }
}
