package PersonalArea.backend.controller;

import PersonalArea.backend.Entity.*;
import PersonalArea.backend.FileUploadService.FileStorageService;
import PersonalArea.backend.repository.MemoryRepository;
import PersonalArea.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("api/user")
public class MemoriesController {

  @Autowired
  MemoryRepository memoryRepository;

  @Autowired
  UserRepository userRepository;

  @Autowired
  private FileStorageService storageService;

  @Autowired
  private FileStorageService fileStorageService;

  @PostMapping("/memory/save")
  public String saveMemory(
      @RequestParam Long userId,
      @RequestParam Long memoryId,
      @RequestParam String memoryName) {
    try {
      Memory memory = memoryRepository.findAllById(memoryId);
      Memory memoryToSave =
          new Memory(
              null,
              memoryName,
              null,
              null,
              userId);
      memoryRepository.save(memoryToSave);
      memory.getMemories().add(memoryToSave);
      memoryRepository.save(memory);
      return "Accepted";
    } catch (Exception e) {
      return e.getMessage();
    }
  }

  @PostMapping("/memory/saveFiles")
  public String saveFilesToMemory(
      @RequestParam Long memoryId,
      @RequestPart("files") MultipartFile[] files) throws IOException {
    try {
      Memory memory = memoryRepository.findAllById(memoryId);
      for (MultipartFile file : files) {
        FileDB fileDB = storageService.store(file);
        memory.getFileDBSet().add(fileDB);
      }
      memoryRepository.save(memory);
      return "Accepted";
    } catch (Exception e) {
      return e.getMessage();
    }
  }

  @GetMapping("/memory/getOne/{userId}/{memoryId}")
  public Memory memories(@PathVariable Long userId,
                         @PathVariable Long memoryId) {
    try {
     Memory memory = memoryRepository.findAllById(memoryId);
      if (memory.getUserId().equals(userId)) {
        return memory;
      }
      else {
        return null;
      }
    } catch (Exception e) {
      return null;
    }
  }

  @GetMapping("/memory/getMain/{userId}")
  public Memory memories(@PathVariable Long userId) {
    try {
      return userRepository.getOne(userId).getMemory();
    } catch (Exception e) {
      return null;
    }
  }
}
