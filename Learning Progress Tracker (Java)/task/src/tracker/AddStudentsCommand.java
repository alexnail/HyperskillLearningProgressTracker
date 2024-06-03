package tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddStudentsCommand implements Command {
    private final Scanner scanner;

    public AddStudentsCommand(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public boolean execute() {
        System.out.println("Enter student credentials or 'back' to return:");
        List<Student> students = new ArrayList<>();
        String input;
        do {
            input = scanner.nextLine();
            if (!input.equalsIgnoreCase("back")) {
                StudentValidator validator = Student.getValidator();
                if (validator.isValid(input)) {
                    Student student = Student.fromString(input);
                    students.add(student);
                    System.out.println("The student has been added.");
                } else {
                    System.out.println(validator.getMessage());
                }
            }
        } while (!input.equalsIgnoreCase("back"));

        System.out.printf("Total %d students have been added.%n", students.size());
        return false;
    }
}
