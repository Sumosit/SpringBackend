package PersonalArea.backend.controllerWebsocket;

import PersonalArea.backend.Entity.ChatId;
import PersonalArea.backend.Entity.User;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;
////    @OneToOne
//    private Long chatId;
    @OneToOne
    private User sender;
}
