package tracker;

public class BackCommand implements Command {
    @Override
    public boolean execute() {
        System.out.println("Enter 'exit' to exit the program.");
        return false;
    }
}
