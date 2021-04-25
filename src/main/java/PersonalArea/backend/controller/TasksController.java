package PersonalArea.backend.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
public class TasksController {

  @PostMapping("/admin/task/save")
  public String saveTask(
      @RequestParam String title,
      @RequestParam String description,
      @RequestParam Timestamp sendDate,
      @RequestParam Timestamp deadline,
      @RequestPart("files") MultipartFile[] files,
      @RequestParam Long authorId
  ) {
    try {
      return "Task accepted";
    } catch (Exception e) {
      return null;
    }
  }
}
