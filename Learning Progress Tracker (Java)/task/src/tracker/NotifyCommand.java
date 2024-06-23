package tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class NotifyCommand implements Command {

    private final static List<String> COURSES = List.of("java", "dsa", "databases", "spring");
    private final static List<Integer> COMPLETION_POINTS = List.of(600, 400, 480, 550);

    private final StudentDAO studentDao = InMemoryStudentDAO.dao();
    private final NotificationDAO notificationDao = InMemoryNotificationDAO.dao();

    @Override
    public boolean execute() {
        Map<String, Student> all = studentDao.findAll();
        List<Notification> notifications = all.values().stream()
                .map(this::getNotifications)
                .flatMap(List::stream)
                .toList();
        notifications.forEach(this::sendNotification);
        long notified = notifications.stream()
                .map(notification -> notification.student)
                .distinct().count();
        System.out.println("Total %d students have been notified.".formatted(notified));
        return false;
    }

    private void sendNotification(Notification notification) {
        Student student = notification.student;
        String message = """
                To: %s
                Re: Your Learning Progress
                Hello, %s %s! You have accomplished our %s course!"""
                .formatted(student.getEmail(), student.getFirstName(), student.getLastName(), notification.course);
        System.out.println(message);
        // save to dao
        notificationDao.save(notification);
    }

    private List<Notification> getNotifications(Student student) {
        List<Notification> notifications = new ArrayList<>();
        Submission total = student.getTotal();
        if (total.java() >= COMPLETION_POINTS.get(0)) {
            Notification java = new Notification(student, "Java");
            if (!notificationDao.contains(java)) {
                notifications.add(java);
            }
        }
        if (total.dsa() >= COMPLETION_POINTS.get(1)) {
            Notification dsa = new Notification(student, "DSA");
            if (!notificationDao.contains(dsa)) {
                notifications.add(dsa);
            }
        }
        if (total.dbs() >= COMPLETION_POINTS.get(2)) {
            Notification databases = new Notification(student, "Databases");
            if (!notificationDao.contains(databases)) {
                notifications.add(databases);
            }
        }
        if (total.spring() >= COMPLETION_POINTS.get(3)) {
            Notification spring = new Notification(student, "Spring");
            if (!notificationDao.contains(spring)) {
                notifications.add(spring);
            }
        }
        return notifications;
    }

    public record Notification(Student student, String course) {
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Notification that = (Notification) o;
            return Objects.equals(course, that.course) && Objects.equals(student, that.student);
        }

        @Override
        public int hashCode() {
            int result = Objects.hashCode(student);
            result = 31 * result + Objects.hashCode(course);
            return result;
        }
    }
}
