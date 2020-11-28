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
  public User addRoleToUser(@RequestParam Long id) {
    User user = userRepository.getOne(id);
    Set<Role> roles = user.getRoles();
    Role role = new Role();
    role.setId(null);
    role.setName(ERole.ROLE_ADMIN);
    roles.add(role);
    user.setRoles(roles);
//    userRepository.save(user);
    return user;
  }
}
