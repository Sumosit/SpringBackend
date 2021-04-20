package PersonalArea.backend.controllerWebsocket;

import PersonalArea.backend.Entity.ChatId;
import PersonalArea.backend.Entity.User;
import PersonalArea.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

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
  public ChatMessage greeting(ChatRestForm chatRestForm) throws Exception {
    Thread.sleep(1000);
    ChatMessage chatMessage =
        new ChatMessage(
            null,
            chatRestForm.getMessage(),
            userRepository.findUserById(chatRestForm.getChatId()),
            userRepository.findUserById(chatRestForm.getUserId())
        );
    Optional<ChatId> isNull = chatIdRepository.findById(chatRestForm.getChatId());
    if (isNull.isPresent()) {
      ChatId chatId = isNull.get();
      Set<ChatMessage> chatMessageSet = chatId.getChatMessageSet();
      chatMessageRepository.save(chatMessage);
      chatMessageSet.add(chatMessage);
      chatId.setChatMessageSet(chatMessageSet);
      chatIdRepository.save(chatId);
      System.out.println(chatId);
      return new ChatMessage(
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
        chatIdRepository.save(new ChatId(
            null,
            userRepository.findUserById(id1),
            userRepository.findUserById(id2),
            null));
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
    User user = userRepository.findUserById(user_id);
    return chatIdRepository.findAllBySenderOrRecipient(user, user);
  }
}
