package PersonalArea.backend.Entity;

import PersonalArea.backend.controllerWebsocket.ChatMessage;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ChatId {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  private User sender;
  @OneToOne
  private User recipient;
  @OneToMany(fetch = FetchType.EAGER)
  private Set<ChatMessage> chatMessageSet;
}
