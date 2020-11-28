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
@RequestMapping("/api/qwerty")
public class ByPassController {

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  UserRepository userRepository;

  @GetMapping("add/role")
  public String addRoleToUser(@RequestParam String email,
                              @RequestParam String rolename) {
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
}
