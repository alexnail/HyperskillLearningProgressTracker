package tracker;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tracker.exceptions.DuplicateEmailException;

import static org.assertj.core.api.Assertions.assertThat;

class StatisticsCalculatorTest {

    private final StudentDAO dao = InMemoryStudentDAO.dao();

    @BeforeEach
    void setUp() throws DuplicateEmailException {
        Student student = new Student();
        student.setId("10000");
        student.setEmail("a@b.c");
        student.addSubmission(new Submission(8, 7, 7, 5));
        student.addSubmission(new Submission(7, 6, 9, 7));
        student.addSubmission(new Submission(6, 5, 5, 0));
        dao.save(student);

        Student student2 = new Student();
        student2.setId("10001");
        student.setEmail("d@g.e");
        student2.addSubmission(new Submission(8, 0, 8, 6));
        student2.addSubmission(new Submission(7, 0, 0, 0));
        student2.addSubmission(new Submission(9, 0, 0, 5));
        dao.save(student2);
    }

    @AfterEach
    void tearDown() {
        dao.reset();
    }

    @Test
    void mostPopular() {
        assertThat(StatisticsCalculator.mostPopular(dao.findAll())).isEqualTo("Java, Databases, Spring");
    }

    @Test
    void leastPopular() {
        assertThat(StatisticsCalculator.leastPopular(dao.findAll())).isEqualTo("DSA");
    }

    @Test
    void highestActivity() {
        assertThat(StatisticsCalculator.highestActivity(dao.findAll())).isEqualTo("Java");
    }

    @Test
    void lowestActivity() {
        assertThat(StatisticsCalculator.lowestActivity(dao.findAll())).isEqualTo("DSA");
    }

    @Test
    void easiestCourse() {
        assertThat(StatisticsCalculator.easiestCourse(dao.findAll())).isEqualTo("Java");
    }

    @Test
    void hardestCourse() {
        assertThat(StatisticsCalculator.hardestCourse(dao.findAll())).isEqualTo("Spring");
    }
}