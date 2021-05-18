package PersonalArea.backend.controller;

import PersonalArea.backend.Entity.Document;
import PersonalArea.backend.Entity.FileDB;
import PersonalArea.backend.Entity.User;
import PersonalArea.backend.Entity.UserExtra;
import PersonalArea.backend.FileUploadService.FileStorageService;
import PersonalArea.backend.repository.DocumentRepository;
import PersonalArea.backend.repository.UserExtraRepository;
import PersonalArea.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("api/user")
public class UserProfileController {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private FileStorageService storageService;

  @Autowired
  private UserExtraRepository userExtraRepository;

  @Autowired
  private DocumentRepository documentRepository;

  @GetMapping("/one/{userId}")
  public User getUser(
      @PathVariable Long userId
  ){
    try {
      return userRepository.findUserById(userId);
    } catch (Exception e) {
      return null;
    }
  }

  @GetMapping("/userExtra/{userId}")
  public UserExtra getUserExtra(
      @PathVariable Long userId
  ) {
    try {
      return userRepository.findUserById(userId).getUserExtra();
    } catch (Exception e) {
      return null;
    }
  }

  @PostMapping("/nameSurnUserProf/save")
  public String saveNameSurnUserProf(
      @RequestParam String name,
      @RequestParam String surname,
      @RequestParam String username,
      @RequestParam String profession,
      @RequestParam Long userId
  ) {
    try {
      User user = userRepository.findUserById(userId);
      user.setName(name);
      user.setSurname(surname);
      user.setUsername(username);
      user.setProfession(profession);
      userRepository.save(user);
      return "Accepted";
    } catch (Exception e) {
      return null;
    }
  }

  @PostMapping("/userExtra/file/save")
  public String saveFileToUserExtra(
      @RequestParam("file") MultipartFile file,
      @RequestParam String currentFile,
      @RequestParam Long userExtraId
      ) {
    try {
      FileDB fileDB = storageService.store(file);
      UserExtra userExtra = userExtraRepository.findUserExtraById(userExtraId);
      for (Document document: userExtra.getDocuments()){
        if (document.getName().equals(currentFile)) {
          document.setFileDB(fileDB);
          documentRepository.save(document);
          break;
        }
      }
      userExtraRepository.save(userExtra);
//      switch (currentFile) {
//        case "passport":
//          userExtra.setPassport(fileDB);
//          break;
//        case "diploma":
//          userExtra.setDiploma(fileDB);
//          break;
//        case "medicalVerification":
//          userExtra.setMedicalVerification(fileDB);
//          break;
//        case "conviction":
//          userExtra.setConviction(fileDB);
//          break;
//        case "employmentContract":
//          userExtra.setEmploymentContract(fileDB);
//          break;
//      }
      userExtraRepository.save(userExtra);
      return "Accepted";
    } catch (Exception e) {
      return null;
    }
  }
}
