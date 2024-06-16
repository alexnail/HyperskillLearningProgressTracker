package tracker;

import tracker.exceptions.DuplicateEmailException;

import java.util.LinkedHashMap;
import java.util.Map;

public class InMemoryStudentDAO implements StudentDAO {

    private static StudentDAO instance;

    private final Map<String, Student> students = new LinkedHashMap<>();

    private InMemoryStudentDAO() {}

    public static StudentDAO dao() {
        if (instance == null) {
            instance = new InMemoryStudentDAO();
        }
        return instance;
    }

    @Override
    public Student save(Student student) throws DuplicateEmailException {
        if (students.values().stream()
                .anyMatch(s -> s.getEmail().equals(student.getEmail()))) {
            throw new DuplicateEmailException("This email is already taken.");
        }
        return students.put(student.getId(), student);
    }

    @Override
    public Map<String, Student> findAll() {
        return students;
    }

    @Override
    public int size() {
        return students.size();
    }

    @Override
    public Student find(String studentId) {
        return students.get(studentId);
    }

    @Override
    public Student update(Student student) {
        return students.put(student.getId(), student);
    }
}
