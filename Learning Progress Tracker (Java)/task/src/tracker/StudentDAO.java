package tracker;

import tracker.exceptions.DuplicateEmailException;

import java.util.Map;

public interface StudentDAO {

    Student save(Student student) throws DuplicateEmailException;

    Map<String, Student> getAll();

    int size();
}
