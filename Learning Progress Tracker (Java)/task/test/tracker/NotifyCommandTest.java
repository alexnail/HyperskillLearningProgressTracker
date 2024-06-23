package tracker;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import tracker.NotifyCommand.Notification;
import tracker.exceptions.DuplicateEmailException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class NotifyCommandTest {

    private final StudentDAO studentDAO = InMemoryStudentDAO.dao();
    private final NotificationDAO notificationDAO = InMemoryNotificationDAO.dao();

    private final NotifyCommand command = new NotifyCommand();

    @AfterEach
    void tearDown() {
        studentDAO.reset();
    }

    @Test
    void sendNotification() throws DuplicateEmailException {
        Student john = build("10000", "John", "Doe", "johnd@email.net", 600, 400, 0, 0);
        studentDAO.save(john);
        Student jane = build("10001", "Jane", "Spark", "jspark@yahoo.com", 0, 0, 0, 0);
        studentDAO.save(jane);

        command.execute();

        List<Notification> notifications = notificationDAO.findAll();
        assertThat(notifications).hasSize(2);
    }

    private static Student build(String id, String firstName, String lastName, String email, int java, int dsa, int database, int spring) {
        Student student = new Student();
        student.setId(id);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.addSubmission(new Submission(java, dsa, database, spring));
        return student;
    }
}