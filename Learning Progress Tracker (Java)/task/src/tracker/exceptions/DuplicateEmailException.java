package tracker.exceptions;

public class DuplicateEmailException extends Exception {
    public DuplicateEmailException(String s) {
        super(s);
    }
}
