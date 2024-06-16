package tracker;

import tracker.exceptions.DuplicateEmailException;

public interface StudentDAO {

    Student save(Student student) throws DuplicateEmailException;

    int size();
}
