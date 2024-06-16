package tracker;

import java.util.Scanner;
import java.util.stream.Stream;

public class AddPointsCommand implements Command {
    private final Scanner scanner;
    private final StudentDAO dao;

    public AddPointsCommand(Scanner scanner) {
        this.scanner = scanner;
        dao = InMemoryStudentDAO.dao();
    }

    @Override
    public boolean execute() {
        System.out.println("Enter an id and points or 'back' to return:");
        String input;
        do {
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("back")) {
                return false;
            }
            String[] split = input.split("\\s");
            if (split.length != 5) {
                System.out.println("Incorrect points format.");
                continue;
            }

            String studentId = split[0];
            Student student = dao.find(studentId);
            if (student == null) {
                System.out.printf("No student is found for id=%s.%n", studentId);
                continue;
            }

            try {
                int java = Integer.parseInt(split[1]);
                int dsa = Integer.parseInt(split[2]);
                int dbs = Integer.parseInt(split[3]);
                int spring = Integer.parseInt(split[4]);
                if (Stream.of(java, dsa, dbs, spring).anyMatch(i -> i < 0 )) {
                    throw new NumberFormatException();
                }

                Points points = student.getPoints();
                Points plus = new Points(java, dsa, dbs, spring);
                student.setPoints(points == null ? plus : points.add(plus));
                dao.update(student);
                System.out.println("Points updated.");
            } catch (NumberFormatException e) {
                System.out.println("Incorrect points format.");
            }
        } while (!input.equalsIgnoreCase("back"));

        return false;
    }
}
