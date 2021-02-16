package PersonalArea.backend.controller;

import PersonalArea.backend.Entity.News;
import PersonalArea.backend.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class NewsController {

  @Autowired
  NewsRepository newsRepository;

  @GetMapping("/news")
  public List<News> allNews() {
    return newsRepository.findAll();
  }
}
