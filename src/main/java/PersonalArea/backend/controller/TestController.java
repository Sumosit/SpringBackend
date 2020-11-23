package PersonalArea.backend.controller;

import PersonalArea.backend.models.ERole;
import PersonalArea.backend.models.Role;
import PersonalArea.backend.models.User;
import PersonalArea.backend.repository.RoleRepository;
import PersonalArea.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
  @Autowired
  RoleRepository roleRepository;

  @Autowired
  UserRepository userRepository;

  @GetMapping("/all")
  public String allAccess() {
    return "Public Content.";
  }

  @GetMapping("/user")
  @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
  public String userAccess() {
    return "User Content.";
  }

  @GetMapping("/mod")
  @PreAuthorize("hasRole('MODERATOR')")
  public String moderatorAccess() {
    return "Moderator Board.";
  }

  @GetMapping("/admin")
  @PreAuthorize("hasRole('ADMIN')")
  public String adminAccess() {
    return "Admin Board.";
  }

  @GetMapping("/all/roles")
  public List<Role> allRoles() { return roleRepository.findAll();}

  @GetMapping("/all/users")
  public List<User> allUsers() { return userRepository.findAll();}

//  @GetMapping("/add/roles")
//  public String addRole(@RequestParam String name) {
//    ERole n;
//    Role role = new Role();
//    role.setId(null);
//    if (name.equals("user")) {
//      role.setName(ERole.ROLE_USER);
//      roleRepository.save(role);
//    }else if (name.equals("moderator")) {
//      role.setName(ERole.ROLE_MODERATOR);
//      roleRepository.save(role);
//    }else if (name.equals("admin")) {
//      role.setName(ERole.ROLE_ADMIN);
//      roleRepository.save(role);
//    }
//    return "Added";
//  }
}
