package PersonalArea.backend.controllerWebsocket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatRestForm {
  private Long id;
  private String message;
  private Long chatId;
  private Long userId;
  private Timestamp sendDate;
}
