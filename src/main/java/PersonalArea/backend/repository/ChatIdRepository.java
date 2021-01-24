package PersonalArea.backend.repository;

import PersonalArea.backend.models.ChatId;
import PersonalArea.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface ChatIdRepository extends JpaRepository<ChatId, Long> {
  List<ChatId> findAllBySenderIdOrRecipientId(Long senderId, Long recipientId);
  boolean existsAllBySenderIdAndRecipientId(Long senderId, Long recipientId);
  boolean existsAllByRecipientIdAndSenderId(Long senderId, Long recipientId);
}
