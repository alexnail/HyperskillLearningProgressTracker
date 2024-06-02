package tracker;

public class ExitCommand implements Command {
    @Override
    public boolean execute() {
        System.out.println("Bye!");
        return true;
    }
}
