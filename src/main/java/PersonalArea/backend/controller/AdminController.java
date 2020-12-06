package PersonalArea.backend.controller;

import PersonalArea.backend.models.*;
import PersonalArea.backend.repository.RoleRepository;
import PersonalArea.backend.repository.SalaryRepository;
import PersonalArea.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/admin")
public class AdminController {

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  UserRepository userRepository;

  @Autowired
  SalaryRepository salaryRepository;

  @GetMapping("add/roles")
  public String addRole(@RequestParam String rolename) {
    Role role = new Role();
    role.setId(null);
    if (rolename.equals("ROLE_USER")) {
      role.setName(ERole.ROLE_USER);
      roleRepository.save(role);
    }else if (rolename.equals("ROLE_MODERATOR")) {
      role.setName(ERole.ROLE_MODERATOR);
      roleRepository.save(role);
    }else if (rolename.equals("ROLE_ADMIN")) {
      role.setName(ERole.ROLE_ADMIN);
      roleRepository.save(role);
    }
    return "Accepted";
  }

  @GetMapping("delete/{userid}")
  public String deleteUser(@PathVariable Long userid) {
    userRepository.delete(new User(userid));
    return "Delete Accepted";
  }

  @PostMapping("/add/invoices")
  public String addSalaries(@RequestParam String userid,
                            String amount,
                            String date) {
    Salary salary = new Salary(null, userRepository.getOne((long) Integer.parseInt(userid)),
        Double.parseDouble(amount),
        date);
    System.out.println(salary);
    salaryRepository.save(salary);

    return "Invoices Accepted";
  }
}
