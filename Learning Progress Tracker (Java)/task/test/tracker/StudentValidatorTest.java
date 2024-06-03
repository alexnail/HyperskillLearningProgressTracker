package tracker;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StudentValidatorTest {

    @Test
    void returnNullMessageForValidInput() {
        StudentValidator validator = Student.getValidator();
        assertThat(validator.isValid("John Doe jdoe@mail.net")).isTrue();
        assertThat(validator.getMessage()).isNull();

        assertThat(validator.isValid("Jane Doe jane.doe@yahoo.com")).isTrue();
        assertThat(validator.getMessage()).isNull();
    }

    @Test
    void invalidEmail() {
        StudentValidator validator = Student.getValidator();
        assertThat(validator.isValid("John Doe email")).isFalse();
        assertThat(validator.getMessage()).isEqualTo("Incorrect email.");

        assertThat(validator.isValid("name surname email@emailxyz")).isFalse();
        assertThat(validator.getMessage()).isEqualTo("Incorrect email.");

        assertThat(validator.isValid("name surname email@e@mail.xyz")).isFalse();
        assertThat(validator.getMessage()).isEqualTo("Incorrect email.");
    }

    @Test
    void invalidFirstName() {
        StudentValidator validator = Student.getValidator();
        assertThat(validator.isValid("J. Doe name@domain.com")).isFalse();
        assertThat(validator.getMessage()).isEqualTo("Incorrect first name.");

        assertThat(validator.isValid("nam-'e surname email@email.xyz")).isFalse();
        assertThat(validator.getMessage()).isEqualTo("Incorrect first name.");
    }

    @Test
    void invalidLastName() {
        StudentValidator validator = Student.getValidator();
        assertThat(validator.isValid("John D. name@domain.com")).isFalse();
        assertThat(validator.getMessage()).isEqualTo("Incorrect last name.");
    }

    @Test
    void validLastName() {
        StudentValidator validator = Student.getValidator();
        assertThat(validator.isValid("Robert Jemison Van de Graaff robertvdgraaff@mit.edu")).isTrue();
        assertThat(validator.getMessage()).isNull();
    }
}