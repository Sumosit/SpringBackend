package PersonalArea.backend.controller;

import PersonalArea.backend.repository.RoleRepository;
import PersonalArea.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/qwerty")
public class ByPassController {

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  UserRepository userRepository;
}
