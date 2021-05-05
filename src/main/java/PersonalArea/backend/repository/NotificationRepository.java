package PersonalArea.backend.repository;

import PersonalArea.backend.Entity.Notification;
import PersonalArea.backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
  List<Notification> findAllByUser(User user);
}
