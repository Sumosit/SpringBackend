package PersonalArea.backend.controller;

import PersonalArea.backend.models.Salary;
import PersonalArea.backend.models.User;
import PersonalArea.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("api/user")
public class UserProfileController {

  @Autowired
  UserRepository userRepository;

  @GetMapping("/one/{userId}")
  public User OneUsers(@PathVariable Long userId) { return userRepository.findUserById(userId);}

  @GetMapping("/salaries/{userId}")
  public Set<Salary> getAllSalariesByUserId(@PathVariable Long userId) {
    try {
      return userRepository.getOne(userId).getSalaries();
    } catch (Exception e) {
      return null;
    }
  }

  @GetMapping("/resume/{userId}")
  public String getUserResume(@PathVariable Long userId) {
    try {
      return userRepository.getOne(userId).getResume();
    } catch (Exception e) {
      return null;
    }
  }

  @PostMapping("/send")
  public String sendResume(@RequestParam Long userId,
                           @RequestParam String resume) {
    try {
      User user = userRepository.getOne(userId);
      user.setResume(resume);
      userRepository.save(user);
      System.out.println(user.getResume());
      return user.getResume();
    } catch (Exception e) {
      return null;
    }
  }
}
