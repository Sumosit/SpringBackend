package PersonalArea.backend.controllerWebsocket;

import PersonalArea.backend.Entity.News;
import PersonalArea.backend.Entity.NewsMessage;
import PersonalArea.backend.repository.NewsRepository;
import PersonalArea.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.sql.Timestamp;

@Controller
public class NewWebSocketController {

  @Autowired
  NewsRepository newsRepository;

  @Autowired
  UserRepository userRepository;

  @MessageMapping("/news")
  @SendTo("/topic/news")
  public News news(NewsMessage message) throws Exception {
    News news = new News(
        null,
        message.getAuthorId(),
        message.getAuthorUsername(),
        message.getAuthorAvatar(),
        message.getTitle(),
        message.getContent(),
        Timestamp.valueOf(message.getDate()));
    newsRepository.save(news);
    Thread.sleep(1000); // simulated delay
    return news;
  }
}
