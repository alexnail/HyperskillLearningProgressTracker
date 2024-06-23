package tracker;

import tracker.NotifyCommand.Notification;

import java.util.ArrayList;
import java.util.List;

public class InMemoryNotificationDAO implements NotificationDAO {

    private final List<Notification> notifications = new ArrayList<>();
    private static NotificationDAO instance;

    public static NotificationDAO dao() {
        if (instance == null) {
            instance = new InMemoryNotificationDAO();
        }
        return instance;
    }

    @Override
    public boolean save(Notification notification) {
        return notifications.add(notification);
    }

    @Override
    public boolean contains(Notification notification) {
        return notifications.contains(notification);
    }

    @Override
    public List<Notification> findAll() {
        return notifications;
    }
}
