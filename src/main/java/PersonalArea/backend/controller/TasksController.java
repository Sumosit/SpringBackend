package PersonalArea.backend.controller;

import PersonalArea.backend.Entity.FileDB;
import PersonalArea.backend.Entity.Task;
import PersonalArea.backend.Entity.User;
import PersonalArea.backend.FileUploadService.FileStorageService;
import PersonalArea.backend.repository.TaskRepository;
import PersonalArea.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
public class TasksController {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private TaskRepository taskRepository;

  @Autowired
  private FileStorageService storageService;

  @PostMapping("/admin/task/save")
  public String saveTask(
      @RequestParam String title,
      @RequestParam String description,
      @RequestParam Timestamp sendDate,
      @RequestParam Timestamp deadline,
      @RequestPart(value = "files", required = false) MultipartFile[] files,
      @RequestParam Long[] usersId,
      @RequestParam Long userId
  ) throws IOException {
    try {
      Set<FileDB> fileDBSet = new HashSet<>();
      for (MultipartFile file : files) {
        FileDB fileDB = storageService.store(file);
        fileDBSet.add(fileDB);
      }
      User author = userRepository.findUserById(userId);
      Task task =
          new Task(
              null, title, description,
              sendDate, deadline, fileDBSet,
              author);
      taskRepository.save(task);
      for (int i = 0; i < usersId.length; i++) {
        User user = userRepository.findUserById(usersId[i]);
        user.getTasks().add(task);
        userRepository.save(user);
      }

      return "Task accepted";
    } catch (Exception e) {
      return null;
    }
  }

  @GetMapping("/user/tasks/{userId}")
  public Set<Task> getTasksByUserId(
      @PathVariable Long userId
  ) {
    try {
      return userRepository.findUserById(userId).getTasks();
    } catch (Exception e) {
      return null;
    }
  }
}
