package PersonalArea.backend.controller;

import PersonalArea.backend.Entity.*;
import PersonalArea.backend.repository.EducationRepository;
import PersonalArea.backend.repository.PersonalDataRepository;
import PersonalArea.backend.repository.TrainingRepository;
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

  @Autowired
  PersonalDataRepository personalDataRepository;

  @Autowired
  EducationRepository educationRepository;

  @Autowired
  TrainingRepository trainingRepository;

  @GetMapping("/one/{userId}")
  public User OneUsers(@PathVariable Long userId) {
    return userRepository.findUserById(userId);
  }

  @GetMapping("/salaries/{userId}")
  public Set<Salary> getAllSalariesByUserId(@PathVariable Long userId) {
    try {
      return userRepository.getOne(userId).getSalaries();
    } catch (Exception e) {
      return null;
    }
  }

  @GetMapping("/training/{userId}")
  public Set<Training> getTraining(@PathVariable Long userId) {
    try {
      return userRepository.getOne(userId).getTraining();
    } catch (Exception e) {
      return null;
    }
  }

  @PostMapping("/training/add")
  public Set<Training> addTraining(
      @RequestParam Long userId,
      @RequestParam String date,
      @RequestParam String organization,
      @RequestParam String name,
      @RequestParam String volumeInHours
  ) {
    try {
      User user = userRepository.getOne(userId);
      Training training = new Training(
          null,
          date,
          organization,
          name,
          volumeInHours
      );
      trainingRepository.save(training);
      user.getTraining().add(training);
      userRepository.save(user);
      return user.getTraining();
    } catch (Exception e) {
      return null;
    }
  }

  @GetMapping("/education/{userId}")
  public Set<Education> getEducation(@PathVariable Long userId) {
    try {
      return userRepository.getOne(userId).getEducation();
    } catch (Exception e) {
      return null;
    }
  }
  @PostMapping("/education/add")
  public Set<Education> addEducation(
      @RequestParam Long userId,
      @RequestParam String education,
      @RequestParam String organization,
      @RequestParam String year,
      @RequestParam String specialization,
      @RequestParam String document
  ) {
    try {
      User user = userRepository.getOne(userId);
      Education educ = new Education(
          null,
          education,
          organization,
          year,
          specialization,
          document);
      educationRepository.save(educ);
      user.getEducation().add(educ);
      userRepository.save(user);
      return user.getEducation();
    } catch (Exception e) {
      return null;
    }
  }

  @GetMapping("/personal-data/{userId}")
  public PersonalData getPersonalData(@PathVariable Long userId) {
    try {
      return userRepository.getOne(userId).getPersonalData();
    } catch (Exception e) {
      return null;
    }
  }

  @PostMapping("/personal-data/add")
  public PersonalData addPersonalData(
      @RequestParam Long userId,
      @RequestParam String snils,
      @RequestParam String iin,
      @RequestParam String phoneNumber,
      @RequestParam String location
  ) {
    try {
      User user = userRepository.getOne(userId);
      PersonalData personalData =
          new PersonalData(null, snils, iin, phoneNumber, location);
      personalData.setId(user.getPersonalData().getId());
      personalDataRepository.save(personalData);
      userRepository.save(user);
      return personalData;
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

  @PostMapping("/send-resume")
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
