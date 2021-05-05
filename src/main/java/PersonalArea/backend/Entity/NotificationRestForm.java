package PersonalArea.backend.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRestForm {
  private Long id;
  private String type;
  private String message;
  private String linkToChat;
  private Long fromUserId;
  private Long userId;
}
