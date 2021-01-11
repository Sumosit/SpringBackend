package PersonalArea.backend.controller;

import PersonalArea.backend.models.*;
import PersonalArea.backend.repository.RoleRepository;
import PersonalArea.backend.repository.SalaryRepository;
import PersonalArea.backend.repository.UserRepository;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;


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
    } else if (rolename.equals("ROLE_MODERATOR")) {
      role.setName(ERole.ROLE_MODERATOR);
      roleRepository.save(role);
    } else if (rolename.equals("ROLE_ADMIN")) {
      role.setName(ERole.ROLE_ADMIN);
      roleRepository.save(role);
    }
    return "Accepted";
  }

  @GetMapping("delete/roles")
  public String deleteRole(@RequestParam String rolename) {
    Role role = new Role();
    role.setId(null);
    if (rolename.equals("ROLE_USER")) {
      role.setName(ERole.ROLE_USER);
      roleRepository.delete(role);
    } else if (rolename.equals("ROLE_MODERATOR")) {
      role.setName(ERole.ROLE_MODERATOR);
      roleRepository.delete(role);
    } else if (rolename.equals("ROLE_ADMIN")) {
      role.setName(ERole.ROLE_ADMIN);
      roleRepository.delete(role);
    }
    return "Delete Accepted";
  }

  @GetMapping("delete/{userid}")
  public String deleteUser(@PathVariable Long userid) {
    userRepository.delete(new User(userid));
    return "Delete Accepted";
  }

  @PostMapping("/add/invoices")
  public String addSalaries(@RequestParam("salaries") String salariesjson) {
    salariesjson = "[" + salariesjson + "]";

//    try {
      Gson gson = new Gson();
      Type salaryListType = new TypeToken<ArrayList<Salary>>() {
      }.getType();
      ArrayList<Salary> salaries = gson.fromJson(salariesjson, salaryListType);

      for (Salary salary : salaries) {
        User user = userRepository.getOne(Long.parseLong(String.valueOf(salary.getId())));
        salary.setId(null);
        Set<Salary> userSalaries = user.getSalaries();
        salaryRepository.save(salary);
        userSalaries.add(salary);
        user.setSalaries(userSalaries);
        userRepository.save(user);
        System.out.println(salary);
      }
      return "True";
//    } catch (Exception e) {
//      return "False";
//    }
  }
}
