package PersonalArea.backend.repository;

import PersonalArea.backend.Entity.ChatId;
import PersonalArea.backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatIdRepository extends JpaRepository<ChatId, Long> {
  ChatId findChatIdById(Long chat_id);
  List<ChatId> findAllBySenderOrRecipient(User senderId, User recipientId);
  boolean existsAllBySenderAndRecipient(User senderId, User recipientId);
  boolean existsAllByRecipientAndSender(User senderId, User recipientId);
}
