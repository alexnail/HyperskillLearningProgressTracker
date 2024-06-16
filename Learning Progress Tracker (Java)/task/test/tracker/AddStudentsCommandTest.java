package tracker;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

class AddStudentsCommandTest {

    @Test
    void addingSecondStudentWithTheSameEmailThrowsException() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(("""
                Jane Spark jspark@gmail.com
                Jane Sprocket jspark@gmail.com
                back""").getBytes());
        Scanner scanner = new Scanner(inputStream);
        AddStudentsCommand command = new AddStudentsCommand(scanner);

        boolean execute = command.execute();

        StudentDAO dao = InMemoryStudentDAO.dao();
        assertThat(dao.size()).isEqualTo(1);
    }
}