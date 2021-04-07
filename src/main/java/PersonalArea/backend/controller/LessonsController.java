package PersonalArea.backend.controller;

import PersonalArea.backend.Entity.*;
import PersonalArea.backend.FileUploadService.FileStorageService;
import PersonalArea.backend.repository.LessonsRepository;
import PersonalArea.backend.repository.LessonsTasksRepository;
import PersonalArea.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("api/user")
public class LessonsController {

  @Autowired
  LessonsRepository lessonsRepository;

  @Autowired
  LessonsTasksRepository lessonsTasksRepository;

  @Autowired
  UserRepository userRepository;

  @Autowired
  private FileStorageService fileStorageService;

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
          lessonName,
          null
      );
      lessonsRepository.save(lesson);
      user.getLessons().add(lesson);
      userRepository.save(user);
      return user.getLessons();
    } catch (Exception e) {
      return null;
    }
  }

  @GetMapping("/lessons/delete/{userId}/{lessonId}")
  public String deleteLesson(
      @PathVariable Long userId,
      @PathVariable Long lessonId
  ) {
    try {
      Lessons lessons = lessonsRepository.getOne(lessonId);
      User user = userRepository.getOne(userId);
      user.getLessons().remove(lessons);
      userRepository.save(user);
      lessonsRepository.delete(lessons);
      return null;
    } catch (Exception e) {
      return null;
    }
  }


  @GetMapping("/lessons/tasks/{userId}/{lessonId}")
  public Set<LessonsTasks> getAllLessonsTasksByUserId(@PathVariable Long userId,
                                                       @PathVariable Long lessonId) {
    try {
      for (Lessons lesson: userRepository.getOne(userId).getLessons()) {
        if (lesson.getId().equals(lessonId)) {
          return lesson.getLessonsTasksSet();
        }
      }
    } catch (Exception e) {
      return null;
    }
    return null;
  }

  @GetMapping("/lessons/tasks/{userId}/{lessonId}/{taskId}")
  public LessonsTasks getLessonTaskById(@PathVariable Long userId,
                                        @PathVariable Long lessonId,
                                        @PathVariable Long taskId) {
    try {
      for (Lessons lesson: userRepository.getOne(userId).getLessons()) {
        if (lesson.getId().equals(lessonId)) {
          for (LessonsTasks lessonTask: lesson.getLessonsTasksSet()) {
            if (lessonTask.getId().equals(taskId)) {
              return lessonTask;
            }
          }
        }
      }
    } catch (Exception e) {
      return null;
    }
    return null;
  }

  @GetMapping("/lessons/tasks/delete/{userId}/{lessonId}/{taskId}")
  public LessonsTasks deleteLessonTaskById(@PathVariable Long userId,
                                        @PathVariable Long lessonId,
                                        @PathVariable Long taskId) {
    try {
      User user = userRepository.getOne(userId);
      for (Lessons lesson: user.getLessons()) {
        if (lesson.getId().equals(lessonId)) {
          LessonsTasks lessonsTask = lessonsTasksRepository.getOne(taskId);
          lesson.getLessonsTasksSet().remove(lessonsTask);
          userRepository.save(user);
          lessonsTasksRepository.delete(lessonsTask);
          return null;
        }
      }
    } catch (Exception e) {
      return null;
    }
    return null;
  }

  @PostMapping("/lessons/tasks/save")
  public Set<LessonsTasks> saveLessonsTask(@RequestParam Long userId,
                                           @RequestParam String name,
                                           @RequestParam String description,
                                           @RequestParam Long lessonId,
                                           @RequestPart("files") MultipartFile[] files) {
    try {
      User user = userRepository.getOne(userId);
      Set<Lessons> lessons = user.getLessons();
      Set<FileDB> fileDBSet = new HashSet<>();
      for (MultipartFile file : files) {
        fileDBSet.add(fileStorageService.store(file));
//        fileDBSet.add(fileStorageService.getStorageFile(file.get));
      }
      for (Lessons lesson: userRepository.getOne(userId).getLessons()) {
        if (lesson.getId().equals(lessonId)) {
          return lesson.getLessonsTasksSet();
        }
      }
    } catch (Exception e) {
      return null;
    }
    return null;
  }
}
