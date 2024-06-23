package tracker;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import tracker.exceptions.DuplicateEmailException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class StatisticsCalculatorTest {

    private final StudentDAO dao = InMemoryStudentDAO.dao();

    private StatisticsCalculator calculator;

    @AfterEach
    void tearDown() {
        dao.reset();
    }

    @Test
    void calculate() throws DuplicateEmailException {
        Student student1 = build("10000", "a@b.c", 8, 7, 7, 5);
        student1.addSubmission(new Submission(7, 6, 9, 7));
        student1.addSubmission(new Submission(6, 5, 5, 0));
        dao.save(student1);

        Student student2 = build("10001", "d@g.e", 8, 0, 8, 6);
        student2.addSubmission(new Submission(7, 0, 0, 0));
        student2.addSubmission(new Submission(9, 0, 0, 5));
        dao.save(student2);

        calculator = StatisticsCalculator.get(dao);

        assertThat(calculator.mostPopular()).isEqualTo(List.of("Java", "Databases", "Spring"));
        assertThat(calculator.leastPopular()).isEqualTo(List.of("DSA"));
        assertThat(calculator.highestActivity()).isEqualTo(List.of("Java"));
        assertThat(calculator.lowestActivity()).isEqualTo(List.of("DSA"));
        assertThat(calculator.easiestCourse()).isEqualTo(List.of("Java"));
        //assertThat(calculator.hardestCourse()).isEqualTo(List.of("Spring"));
    }

    @Test
    void calculate2() throws DuplicateEmailException {
        Student student1 = build("571df244-1dcd-4cde-859e-f371eb3ce318", "address1@mail.com", 5, 4, 3, 1);
        dao.save(student1);
        Student student2 = build("bf43b705-a731-41e8-9a87-634ae078ff74", "address2@mail.com", 5, 4, 3, 1);
        dao.save(student2);
        Student student3 = build("1f751f3a-8d9e-4080-a9d5-78699254e89d", "address3@mail.com", 5, 4, 3, 1);
        dao.save(student3);
        Student student4 = build("b9593dbc-30de-4d72-8343-c2605d23ebea", "address4@mail.com", 5, 4, 3, 1);
        dao.save(student4);

        calculator = StatisticsCalculator.get(dao);

        assertThat(calculator.mostPopular()).isEqualTo(List.of("Java", "DSA", "Databases", "Spring"));
        assertThat(calculator.leastPopular()).isEmpty();
        assertThat(calculator.highestActivity()).isEqualTo(List.of("Java", "DSA", "Databases", "Spring"));
        assertThat(calculator.lowestActivity()).isEmpty();
        assertThat(calculator.easiestCourse()).isEqualTo(List.of("Java"));
        assertThat(calculator.hardestCourse()).isEqualTo(List.of("Spring"));
    }

    private static Student build(String id, String email, int java, int dsa, int database, int spring) {
        Student student = new Student();
        student.setId(id);
        student.setEmail(email);
        student.addSubmission(new Submission(java, dsa, database, spring));
        return student;
    }
}