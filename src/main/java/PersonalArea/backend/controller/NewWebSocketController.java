package PersonalArea.backend.controller;

import PersonalArea.backend.models.News;
import PersonalArea.backend.models.NewsMessage;
import PersonalArea.backend.repository.NewsRepository;
import PersonalArea.backend.websocket.Greeting;
import PersonalArea.backend.websocket.HelloMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class NewWebSocketController {

  @Autowired
  NewsRepository newsRepository;

  @MessageMapping("/news")
  @SendTo("/topic/news")
  public News news(NewsMessage message) throws Exception {
    News news = new News(null, message.getTitle(), message.getContent());
    newsRepository.save(news);
    Thread.sleep(1000); // simulated delay
    return news;
  }
}
