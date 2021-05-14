package PersonalArea.backend.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import PersonalArea.backend.Entity.*;
import PersonalArea.backend.payload.request.LoginRequest;
import PersonalArea.backend.payload.request.SignupRequest;
import PersonalArea.backend.payload.response.JwtResponse;
import PersonalArea.backend.payload.response.MessageResponse;
import PersonalArea.backend.repository.MemoryRepository;
import PersonalArea.backend.repository.RoleRepository;
import PersonalArea.backend.repository.UserExtraRepository;
import PersonalArea.backend.repository.UserRepository;
import PersonalArea.backend.security.jwt.JwtUtils;
import PersonalArea.backend.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  MemoryRepository memoryRepository;

  @Autowired
  UserExtraRepository userExtraRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

    return ResponseEntity.ok(new JwtResponse(jwt,
        userDetails.getId(),
        userDetails.getUsername(),
        userDetails.getName(),
        userDetails.getSurname(),
        userDetails.getEmail(),
        userDetails.getUserExtra(),
        userDetails.getMemory(),
        userDetails.getReminders(),
        userDetails.getTasks(),
        userDetails.getResume(),
        userDetails.getFileDB(),
        roles));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Email is already in use!"));
    }

    // Create new user's account
    User user = new User(signUpRequest.getUsername(),
        signUpRequest.getEmail(),
        encoder.encode(signUpRequest.getPassword()));
    userRepository.save(user);
    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();
    UserExtra userExtra = new UserExtra();
    Memory memory = new Memory();
    memory.setUserId(user.getId());
    memory.setName(user.getUsername()+" memory");

    userExtraRepository.save(userExtra);
    memoryRepository.save(memory);

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);
        userRole = roleRepository.findByName(ERole.ROLE_ADMIN)
            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
//          case "admin":
//            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
//                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//            roles.add(adminRole);
//
//            break;
//          case "mod":
//            Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
//                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//            roles.add(modRole);
//
//            break;
          default:
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        }
      });
    }

    user.setMemory(memory);
    user.setUserExtra(userExtra);
    user.setRoles(roles);
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }
}
