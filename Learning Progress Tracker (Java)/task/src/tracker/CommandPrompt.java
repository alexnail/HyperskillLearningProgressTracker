package tracker;

import java.util.Scanner;

public class CommandPrompt {

    private final Scanner scanner;

    public CommandPrompt(Scanner scanner) {
        this.scanner = scanner;
    }

    public Command getCommand() {
        String input = scanner.nextLine().trim();
        return switch (input) {
            case "exit" -> new ExitCommand();
            case "back" -> new BackCommand();
            case "add students" -> new AddStudentsCommand(scanner);
            case "list" -> new ListStudentsCommand();
            case "add points" -> new AddPointsCommand(scanner);
            case "find" -> new FindCommand(scanner);
            case "statistics" -> new StatisticsCommand(scanner);
            case "" -> () -> {
                System.out.println("No input.");
                return false;
            };
            default -> () -> {
                System.out.println("Unknown command!");
                return false;
            };
        };
    }
}
