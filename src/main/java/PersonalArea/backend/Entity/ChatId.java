package PersonalArea.backend.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatId {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long senderId;
  private Long recipientId;
//  @OneToMany
//  private Set<ChatHistory> chatHistorySet;
}
