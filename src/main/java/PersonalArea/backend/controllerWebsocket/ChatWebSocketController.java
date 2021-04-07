package PersonalArea.backend.controllerWebsocket;

import PersonalArea.backend.Entity.ChatHistory;
import PersonalArea.backend.Entity.ChatId;
import PersonalArea.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ChatWebSocketController {

  @Autowired
  NewsRepository newsRepository;

  @Autowired
  UserRepository userRepository;

  @Autowired
  ChatIdRepository chatIdRepository;

  @Autowired
  ChatHistoryRepository chatHistoryRepository;

  @Autowired
  ChatMessageRepository chatMessageRepository;

  @MessageMapping("/chat/{id}")
  @SendTo("/topic/chat/{id}")
  public ChatRestForm greeting(ChatMessage chatMessage) throws Exception {
    Thread.sleep(1000);
    Optional<ChatId> isNull = chatIdRepository.findById(chatMessage.getChatId());
    if (isNull.isPresent()) {
      ChatId chatId = isNull.get();
      Set<ChatMessage> chatMessageSet = chatId.getChatMessageSet();
      chatMessageRepository.save(chatMessage);
      chatMessageSet.add(chatMessage);
      chatId.setChatMessageSet(chatMessageSet);
      chatIdRepository.save(chatId);
      System.out.println(chatId);
      return new ChatRestForm(
          chatMessage.getId(),
          HtmlUtils.htmlEscape(
              chatMessage.getMessage()),
          chatMessage.getChatId(),
          chatMessage.getUserId());
    }
    return null;
  }

  @PostMapping("/api/chat/add")
  public String createChat(@RequestParam Long id1, Long id2) {
    try {
      if (!chatIdRepository.existsAllBySenderIdAndRecipientId(id1, id2)
          && !chatIdRepository.existsAllByRecipientIdAndSenderId(id1, id2)) {
        chatIdRepository.save(new ChatId(null, id1, id2, null));
        return "True";
      } else {
        return "This chat already exists";
      }
    } catch (Exception e) {
      return "Error";
    }
  }

  @GetMapping("/api/chat/one/{chatId}")
  public Set<ChatMessage> getOneChatById(@PathVariable Long chatId) {
    return chatIdRepository.findById(chatId).get().getChatMessageSet();
  }

  @GetMapping("/api/chat/all/{user_id}")
  public List<ChatId> getAllChatById(@PathVariable Long user_id) {
    return chatIdRepository.findAllBySenderIdOrRecipientId(user_id, user_id);
  }
}
