package tracker;

import java.util.List;

import static tracker.NotifyCommand.*;

public interface NotificationDAO {
    boolean save(Notification notification);

    boolean contains(Notification java);

    List<Notification> findAll();
}
