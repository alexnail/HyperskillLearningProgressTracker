package tracker;

public class StudentValidator {

    private String message;

    public boolean isValid(String input) {
        if (input == null || input.isEmpty() || input.isBlank()) {
            message = "Incorrect credentials.";
            return false;
        }
        String[] split = input.split(" ");
        if (split.length < 3) {
            message = "Incorrect credentials.";
            return false;
        }
        String firstName = input.substring(0, input.indexOf(" "));
        if (firstName.length() < 2
                || !firstName.matches("[a-zA-Z-']+")
                || firstName.matches(".*(-'|'-|--|'').*")
                || firstName.startsWith("'") || firstName.endsWith("'")
                || firstName.startsWith("-") || firstName.endsWith("-")) {
            message = "Incorrect first name.";
            return false;
        }
        String lastName = input.substring(input.indexOf(" ") + 1, input.lastIndexOf(" "));
        if (lastName.length() < 2
                || !lastName.matches("[a-zA-Z-'\\s]+")
                || lastName.matches(".*(-'|'-|--|'').*")
                || lastName.startsWith("'") || lastName.endsWith("'")
                || lastName.startsWith("-") || lastName.endsWith("-")
        ) {
            message = "Incorrect last name.";
            return false;
        }
        String email = input.substring(input.lastIndexOf(" ") + 1);
        if (!email.matches("[A-Za-z0-9+_.-]+@[A-Za-z0-9]+\\.[a-z0-9]+")) {
            message = "Incorrect email.";
            return false;
        }
        return true;
    }

    public String getMessage() {
        return message;
    }
}
