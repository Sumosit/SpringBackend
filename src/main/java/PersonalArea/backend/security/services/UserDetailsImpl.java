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
  private String username, name, surname;
  private String email;
  private UserExtra userExtra;
  private Memory memory;
  private Set<Notes> reminders;
  private Set<Task> tasks;
  private String resume;
  private FileDB fileDB;

  @JsonIgnore
  private String password;

  private Collection<? extends GrantedAuthority> authorities;

  public UserDetailsImpl(Long id, String username, String name, String surname, String email, UserExtra userExtra, Memory memory, Set<Notes> reminders, Set<Task> tasks, String resume, FileDB fileDB, String password, Collection<? extends GrantedAuthority> authorities) {
    this.id = id;
    this.username = username;
    this.name = name;
    this.surname = surname;
    this.email = email;
    this.userExtra = userExtra;
    this.memory = memory;
    this.reminders = reminders;
    this.tasks = tasks;
    this.resume = resume;
    this.fileDB = fileDB;
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
        user.getName(),
        user.getSurname(),
        user.getEmail(),
        user.getUserExtra(),
        user.getMemory(),
        user.getNotes(),
        user.getTasks(),
        user.getResume(),
        user.getFileDB(),
        user.getPassword(),
        authorities);
  }

  public UserExtra getUserExtra() {
    return userExtra;
  }

  public void setUserExtra(UserExtra userExtra) {
    this.userExtra = userExtra;
  }

  public void setMemory(Memory memory) {
    this.memory = memory;
  }

  public void setReminders(Set<Notes> reminders) {
    this.reminders = reminders;
  }

  public Set<Task> getTasks() {
    return tasks;
  }

  public void setTasks(Set<Task> tasks) {
    this.tasks = tasks;
  }

  public void setResume(String resume) {
    this.resume = resume;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
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

  public Memory getMemory() {
    return memory;
  }

  public Set<Notes> getReminders() {
    return reminders;
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
