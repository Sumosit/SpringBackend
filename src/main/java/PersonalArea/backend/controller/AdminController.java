package PersonalArea.backend.controller;

import PersonalArea.backend.models.ERole;
import PersonalArea.backend.models.Role;
import PersonalArea.backend.models.User;
import PersonalArea.backend.repository.RoleRepository;
import PersonalArea.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/admin")
public class AdminController {

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  UserRepository userRepository;

  @GetMapping("add/role/{email}/{rolename}")
  public String addRoleToUser(@PathVariable String email,
                              String rolename) {
    User user = userRepository.findByEmail(email);
    Set<Role> roles = user.getRoles();
    if (rolename.equals("ROLE_USER")) {
      roles.add(new Role(ERole.ROLE_USER));
    }else if (rolename.equals("ROLE_MODERATOR")) {
      roles.add(new Role(ERole.ROLE_MODERATOR));
    }else if (rolename.equals("ROLE_ADMIN")) {
      roles.add(new Role(ERole.ROLE_ADMIN));
    }
    user.setRoles(roles);
    userRepository.save(user);
    return "Accepted";
  }

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
}
