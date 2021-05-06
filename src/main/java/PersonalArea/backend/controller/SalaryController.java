package PersonalArea.backend.controller;

import PersonalArea.backend.Entity.Salary;
import PersonalArea.backend.Entity.User;
import PersonalArea.backend.repository.SalaryRepository;
import PersonalArea.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
public class SalaryController {

  @Autowired
  SalaryRepository salaryRepository;

  @Autowired
  UserRepository userRepository;

  @PostMapping("/admin/salary/save")
  public String saveSalaries(
      @RequestParam Timestamp period,
      @RequestParam int days,
      @RequestParam int hours,
      @RequestParam String month,
      @RequestParam double salary,
      @RequestParam double opv,
      @RequestParam double oneMzp,
      @RequestParam double vosms,
      @RequestParam double totalNalog,
      @RequestParam double ipn,
      @RequestParam double withheld,
      @RequestParam double debt,
      @RequestParam Long userId,
      @RequestParam Long[] usersId
  ) {
    try {
      User author = userRepository.findUserById(userId);
      for (int i = 0; i < usersId.length; i++) {
        User user = userRepository.findUserById(usersId[i]);
        Salary userSalary =
            new Salary(
                null,
                user,
                author,
                period,
                days,
                hours,
                month,
                salary,
                opv,
                oneMzp,
                vosms,
                totalNalog,
                ipn,
                withheld,
                debt
            );
        salaryRepository.save(userSalary);
      }
      return "Accepted";
    } catch (Exception e) {
      return e.getMessage();
    }
  }
}
