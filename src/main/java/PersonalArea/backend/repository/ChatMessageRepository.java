package PersonalArea.backend.repository;

import PersonalArea.backend.controllerWebsocket.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
}
