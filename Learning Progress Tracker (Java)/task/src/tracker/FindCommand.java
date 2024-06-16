package tracker;

import java.util.Scanner;

public class FindCommand implements Command {
    private final Scanner scanner;
    private final StudentDAO dao;

    public FindCommand(Scanner scanner) {
        this.scanner = scanner;
        dao = InMemoryStudentDAO.dao();
    }

    @Override
    public boolean execute() {
        System.out.println("Enter an id or 'back' to return:");
        String input = scanner.nextLine();
        while (!input.equalsIgnoreCase("back")) {
            Student student = dao.find(input);
            if (student != null) {
                Points points = student.getPoints();
                System.out.printf("%s points: Java=%d; DSA=%d; Databases=%d; Spring=%d%n",
                        student.getId(), points.java(), points.dsa(), points.dbs(), points.spring());
            } else {
                System.out.printf("No student is found for id=%s.%n", input);
            }
            input = scanner.nextLine();
        }
        return false;
    }
}
