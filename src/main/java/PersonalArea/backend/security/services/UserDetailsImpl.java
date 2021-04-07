package PersonalArea.backend.security.services;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import PersonalArea.backend.Entity.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDetailsImpl implements UserDetails {
  private static final long serialVersionUID = 1L;

  private Long id;
  private String username;
  private String email;
  private String resume;
  private FileDB fileDB;
  private PersonalData personalData;
  private Set<Education> educationSet;
  private Set<Training> trainingSet;
  private Set<Lessons> lessonsSet;
  private Set<Notes> reminders;
  private Set<Salary> salaries;

  @JsonIgnore
  private String password;

  private Collection<? extends GrantedAuthority> authorities;

  public UserDetailsImpl(Long id, String username, String email, String resume,
                         FileDB fileDB,
                         PersonalData personalData,
                         Set<Education> educationSet,
                         Set<Training> trainingSet,
                         Set<Lessons> lessonsSet,
                         Set<Notes> reminders,
                         Set<Salary> salaries,
                         String password,
                         Collection<? extends GrantedAuthority> authorities) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.resume = resume;
    this.fileDB = fileDB;
    this.personalData = personalData;
    this.educationSet = educationSet;
    this.trainingSet = trainingSet;
    this.lessonsSet = lessonsSet;
    this.reminders = reminders;
    this.salaries = salaries;
    this.password = password;
    this.authorities = authorities;
  }

  public static UserDetailsImpl build(User user) {
    List<GrantedAuthority> authorities = user.getRoles().stream()
        .map(role -> new SimpleGrantedAuthority(role.getName().name()))
        .collect(Collectors.toList());

    return new UserDetailsImpl(
        user.getId(),
        user.getUsername(),
        user.getEmail(),
        user.getResume(),
        user.getFileDB(),
        user.getPersonalData(),
        user.getEducation(),
        user.getTraining(),
        user.getLessons(),
        user.getReminders(),
        user.getSalaries(),
        user.getPassword(),
        authorities);
  }


  public FileDB getFileDB() {
    return fileDB;
  }

  public void setFileDB(FileDB fileDB) {
    this.fileDB = fileDB;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  public Long getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  public String getResume() {
    return resume;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  public PersonalData getPersonalData() {
    return personalData;
  }

  public Set<Education> getEducationSet() {
    return educationSet;
  }

  public Set<Training> getTrainingSet() {
    return trainingSet;
  }

  public Set<Lessons> getLessonsSet() {
    return lessonsSet;
  }

  public Set<Notes> getReminders() {
    return reminders;
  }

  public Set<Salary> getSalaries() {
    return salaries;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    UserDetailsImpl user = (UserDetailsImpl) o;
    return Objects.equals(id, user.id);
  }
}
