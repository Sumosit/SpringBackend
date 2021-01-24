package PersonalArea.backend.controllerWebsocket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatRestForm {
  private String content;
  private Long chatId;
  private Long userId;

}
