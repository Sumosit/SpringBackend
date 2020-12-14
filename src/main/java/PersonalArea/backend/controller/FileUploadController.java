package PersonalArea.backend.controller;

import java.util.List;
import java.util.stream.Collectors;

import PersonalArea.backend.FileUploadService.FileStorageService;
import PersonalArea.backend.FileUploadService.ResponseFile;
import PersonalArea.backend.models.FileDB;
import PersonalArea.backend.models.User;
import PersonalArea.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@CrossOrigin(origins = "*")
public class FileUploadController {

  @Autowired
  private FileStorageService storageService;

  @Autowired
  private UserRepository userRepository;

  @PostMapping("/upload")
  public FileDB uploadFile(@RequestParam("file") MultipartFile file,
                           @RequestParam String userid) {
    String message = "";
    try {
      FileDB fileDB = storageService.store(file);
      User user = userRepository.getOne(Long.parseLong(userid));
      user.setFileDB(fileDB);
      userRepository.save(user);

      message = "Uploaded the file successfully: " + file.getOriginalFilename();
      return fileDB;
    } catch (Exception e) {
      message = "Could not upload the file: " + file.getOriginalFilename() + "!";
//      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
    }
    return null;
  }

  @GetMapping("/files")
  public ResponseEntity<List<ResponseFile>> getListFiles() {
    List<ResponseFile> files = storageService.getAllFiles().map(dbFile -> {
      String fileDownloadUri = ServletUriComponentsBuilder
          .fromCurrentContextPath()
          .path("/files/")
          .path(dbFile.getId())
          .toUriString();

      return new ResponseFile(
          dbFile.getName(),
          fileDownloadUri,
          dbFile.getType(),
          dbFile.getData().length);
    }).collect(Collectors.toList());

    return ResponseEntity.status(HttpStatus.OK).body(files);
  }

  @GetMapping("/files/{id}")
  public ResponseEntity<byte[]> getFile(@PathVariable String id) {
    FileDB fileDB = storageService.getFile(id);

    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
        .body(fileDB.getData());
  }

  @GetMapping("/files/by/{userid}")
  public User getFileByUser(@PathVariable String userid) {

    return userRepository.getOne(Long.parseLong(userid));
  }
}