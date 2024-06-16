package tracker;

import tracker.exceptions.DuplicateEmailException;

import java.util.Map;

public interface StudentDAO {

    Student save(Student student) throws DuplicateEmailException;

    Map<String, Student> findAll();

    int size();

    Student find(String studentId);

    Student update(Student student);

    void reset();
}
