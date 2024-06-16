package tracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tracker.exceptions.DuplicateEmailException;

import java.io.ByteArrayInputStream;
import java.util.Map;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

class AddPointsCommandTest {

    private StudentDAO dao;

    @BeforeEach
    void setUp() throws DuplicateEmailException {
        dao = InMemoryStudentDAO.dao();
        dao.save(buildStudent("10000", "John", "Doe", "jdoe@yahoo.com"));
        dao.save(buildStudent("10001", "Jane", "Spark", "janes@gmail.com"));
    }

    private static Student buildStudent(String id, String firstName, String lastName, String email) {
        Student student = new Student();
        student.setId(id);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        return student;
    }

    @Test
    void testAddPointsCommand() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(("""
                1000 10 10 5 8
                10001 10 10 5 8
                10001 5 8 7 3
                10000 7 7 7 7 7
                10000 -1 2 2 2
                10000 ? 1 1 1
                back""").getBytes());
        Scanner scanner = new Scanner(inputStream);
        AddPointsCommand command = new AddPointsCommand(scanner);

        command.execute();

        Map<String, Student> students = dao.findAll();
        /*assertThat(students.get("10000").getPoints().java()).isEqualTo(7);
        assertThat(students.get("10000").getPoints().dsa()).isEqualTo(7);
        assertThat(students.get("10000").getPoints().dbs()).isEqualTo(7);
        assertThat(students.get("10000").getPoints().spring()).isEqualTo(7);*/

        assertThat(students.get("10001").getPoints().java()).isEqualTo(15);
        assertThat(students.get("10001").getPoints().dsa()).isEqualTo(18);
        assertThat(students.get("10001").getPoints().dbs()).isEqualTo(12);
        assertThat(students.get("10001").getPoints().spring()).isEqualTo(11);
    }
}