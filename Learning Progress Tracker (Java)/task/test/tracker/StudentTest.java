package tracker;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StudentTest {

    @Test
    void createFromString() {
        assertStudentCreatedFromString("John Doe jdoe@mail.net", "John", "Doe", "jdoe@mail.net");
        assertStudentCreatedFromString("Jane Doe jane.doe@yahoo.com", "Jane", "Doe", "jane.doe@yahoo.com");
    }

    private static void assertStudentCreatedFromString(String input, String firstName, String lastName, String email) {
        Student student = Student.fromString(input);

        assertThat(student.getFirstName()).isEqualTo(firstName);
        assertThat(student.getLastName()).isEqualTo(lastName);
        assertThat(student.getEmail()).isEqualTo(email);
    }

    @Test
    void getTotal() {
        Student student = new Student();
        student.setId("1234");
        student.addSubmission(new Submission(1, 2, 3, 4));
        student.addSubmission(new Submission(5, 6, 7, 8));

        assertThat(student.getTotal()).isEqualTo(new Submission(6, 8, 10, 12));
    }
}