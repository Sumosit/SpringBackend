package PersonalArea.backend.controller;

import PersonalArea.backend.models.Salary;
import PersonalArea.backend.models.User;
import PersonalArea.backend.repository.SalaryRepository;
import PersonalArea.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/user")
public class UserProfileController {
  @Autowired
  UserRepository userRepository;

  @GetMapping("/salaries/{userId}")
  public Set<Salary> getAllSalariesByUserId(@PathVariable Long userId) {
    try {
      return userRepository.getOne(userId).getSalaries();
    } catch (Exception e) {
      return null;
    }
  }
}
