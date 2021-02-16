package PersonalArea.backend.controllerWebsocket;

import PersonalArea.backend.Entity.ChatId;
import PersonalArea.backend.repository.ChatIdRepository;
import PersonalArea.backend.repository.NewsRepository;
import PersonalArea.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ChatWebSocketController {

  @Autowired
  NewsRepository newsRepository;

  @Autowired
  UserRepository userRepository;

  @Autowired
  ChatIdRepository chatIdRepository;

  @MessageMapping("/chat/{id}")
  @SendTo("/topic/chat/{id}")
  public ChatRestForm greeting(ChatMessage message) throws Exception {
    Thread.sleep(1000); // simulated delay
    return new ChatRestForm(
        HtmlUtils.htmlEscape(message.getMessage()),
        message.getChatId(),
        message.getUserId());
  }

  @PostMapping("/api/chat/add")
  public String createChat(@RequestParam Long id1, Long id2) {
    try {
      if (!chatIdRepository.existsAllBySenderIdAndRecipientId(id1, id2)
      && !chatIdRepository.existsAllByRecipientIdAndSenderId(id1, id2)) {
        chatIdRepository.save(new ChatId(null, id1, id2));
      }
      else {
        return "This chat already exists";
      }
    } catch (Exception e) {
      return "Error";
    }
    return "Error";
  }

  @GetMapping("/api/chat/all/{user_id}")
  public List<ChatId> getAllChatById(@PathVariable Long user_id) {
    return chatIdRepository.findAllBySenderIdOrRecipientId(user_id, user_id);
  }
}
