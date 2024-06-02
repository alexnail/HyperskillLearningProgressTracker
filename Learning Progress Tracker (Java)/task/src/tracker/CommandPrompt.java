package tracker;

import java.util.Scanner;

public class CommandPrompt {

    private final Scanner scanner;

    public CommandPrompt(Scanner scanner) {
        this.scanner = scanner;
    }

    public Command getCommand() {
        //System.out.println("> ");
        String input = scanner.nextLine().trim();
        return switch (input) {
            case "exit" -> new ExitCommand();
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
