package PersonalArea.backend.controller;

import PersonalArea.backend.Entity.*;
import PersonalArea.backend.repository.RoleRepository;
import PersonalArea.backend.repository.UserRepository;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/admin")
public class AdminController {

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  UserRepository userRepository;

  @GetMapping("/users/all")
  public List<User> adminAllUsers() {
    try {
      return userRepository.findAll();
    } catch (Exception e) {
      return null;
    }
  }

  @GetMapping("/add/roles")
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

  @GetMapping("/delete/roles")
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
}
