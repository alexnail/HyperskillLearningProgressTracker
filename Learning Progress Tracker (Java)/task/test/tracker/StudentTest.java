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
}