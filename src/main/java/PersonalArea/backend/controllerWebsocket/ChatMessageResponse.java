package PersonalArea.backend.controllerWebsocket;

import PersonalArea.backend.Entity.ChatId;
import PersonalArea.backend.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageResponse {
  private Long id;
  private String message;
  private User user;
}
