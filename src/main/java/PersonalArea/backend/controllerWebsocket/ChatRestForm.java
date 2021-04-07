package PersonalArea.backend.controllerWebsocket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatRestForm {
  private Long id;
  private String message;
  private Long chatId;
  private Long userId;
}
