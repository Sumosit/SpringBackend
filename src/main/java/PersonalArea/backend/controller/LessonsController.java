package PersonalArea.backend.controller;

import PersonalArea.backend.Entity.Lessons;
import PersonalArea.backend.Entity.Salary;
import PersonalArea.backend.Entity.Training;
import PersonalArea.backend.Entity.User;
import PersonalArea.backend.repository.LessonsRepository;
import PersonalArea.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("api/user")
public class LessonsController {

  @Autowired
  LessonsRepository lessonsRepository;

  @Autowired
  UserRepository userRepository;

//  @GetMapping("/lesson/{userId}")
//  public Lessons getOneLessonsByUserId(@PathVariable Long userId) {
//    try {
//      return lessonsRepository.getOne(userId);
//    } catch (Exception e) {
//      return null;
//    }
//  }

  @GetMapping("/lessons/{userId}")
  public Set<Lessons> getAllLessonsByUserId(@PathVariable Long userId) {
    try {
      return userRepository.getOne(userId).getLessons();
    } catch (Exception e) {
      return null;
    }
  }

  @PostMapping("/lessons/save")
  public Set<Lessons> saveLesson(
      @RequestParam Long userId,
      @RequestParam String lessonName
  ) {
    try {
      User user = userRepository.getOne(userId);
      Lessons lesson = new Lessons(
          null,
          lessonName
      );
      lessonsRepository.save(lesson);
      user.getLessons().add(lesson);
      userRepository.save(user);
      return user.getLessons();
    } catch (Exception e) {
      return null;
    }
  }
}
