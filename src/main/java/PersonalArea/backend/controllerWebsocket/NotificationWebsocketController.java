package PersonalArea.backend.controllerWebsocket;

import PersonalArea.backend.Entity.Alarms;
import PersonalArea.backend.Entity.Notification;
import PersonalArea.backend.Entity.NotificationRestForm;
import PersonalArea.backend.Entity.User;
import PersonalArea.backend.repository.NotificationRepository;
import PersonalArea.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class NotificationWebsocketController {
  @Autowired
  NotificationRepository notificationRepository;

  @Autowired
  UserRepository userRepository;

  @MessageMapping("/notification/{userId}")
  @SendTo("/topic/notification/{userId}")
  public Notification notification(NotificationRestForm notificationRestForm) throws Exception {
    Thread.sleep(1000);
    try {
      User fromUser = userRepository.findUserById(notificationRestForm.getFromUserId());
      Notification notification =
          new Notification(
              null,
              notificationRestForm.getType() + fromUser.getUsername(),
              notificationRestForm.getMessage(),
              notificationRestForm.getLinkToChat(),
              userRepository.findUserById(notificationRestForm.getFromUserId()),
              userRepository.findUserById(notificationRestForm.getUserId()));
      notificationRepository.save(notification);
      return notification;
    } catch (Exception e) {
      return null;
    }
  }

  @GetMapping("/api/user/notification/all/{userId}")
  public List<Notification> getOneChatMessagesById(@PathVariable Long userId) {
    return notificationRepository.findAllByUser(userRepository.findUserById(userId));
  }
}
