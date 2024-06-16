package tracker;

import java.util.Map;

public class ListStudentsCommand implements Command {
    @Override
    public boolean execute() {
        Map<String, Student> students = InMemoryStudentDAO.dao().findAll();
        if (students.isEmpty()) {
            System.out.println("No students found");
        } else {
            System.out.println("Students:");
            students.forEach((k, v) -> System.out.println(k));
        }
        return false;
    }
}
