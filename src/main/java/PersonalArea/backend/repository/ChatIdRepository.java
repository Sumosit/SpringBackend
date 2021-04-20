package PersonalArea.backend.repository;

import PersonalArea.backend.Entity.ChatId;
import PersonalArea.backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatIdRepository extends JpaRepository<ChatId, Long> {
  List<ChatId> findAllBySenderOrRecipient(User senderId, User recipientId);
  boolean existsAllBySenderIdAndRecipientId(Long senderId, Long recipientId);
  boolean existsAllByRecipientIdAndSenderId(Long senderId, Long recipientId);
}
