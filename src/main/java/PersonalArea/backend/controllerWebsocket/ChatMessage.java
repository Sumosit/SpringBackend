package PersonalArea.backend.controllerWebsocket;

import PersonalArea.backend.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {
    private String message;
    private Long chatId;
    private Long userId;
}