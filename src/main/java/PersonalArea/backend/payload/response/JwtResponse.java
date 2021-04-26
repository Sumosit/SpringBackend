package PersonalArea.backend.payload.response;

import PersonalArea.backend.Entity.*;

import java.util.List;
import java.util.Set;

public class JwtResponse {
  private String token;
  private String type = "Bearer";
  private Long id;
  private String username, name, surname;
  private String email;
  private UserExtra userExtra;
  private Memory memory;
  private Set<Notes> reminders;
  private Set<Task> tasks;
  private String resume;
  private FileDB fileDB;
  private List<String> roles;

  public JwtResponse(String token, Long id, String username, String name, String surname, String email, UserExtra userExtra, Memory memory, Set<Notes> reminders, Set<Task> tasks, String resume, FileDB fileDB, List<String> roles) {
    this.token = token;
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
    this.roles = roles;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
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

  public UserExtra getUserExtra() {
    return userExtra;
  }

  public void setUserExtra(UserExtra userExtra) {
    this.userExtra = userExtra;
  }

  public void setMemory(Memory memory) {
    this.memory = memory;
  }

  public Set<Notes> getReminders() {
    return reminders;
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

  public void setRoles(List<String> roles) {
    this.roles = roles;
  }

  public String getAccessToken() {
    return token;
  }

  public void setAccessToken(String accessToken) {
    this.token = accessToken;
  }

  public String getTokenType() {
    return type;
  }

  public void setTokenType(String tokenType) {
    this.type = tokenType;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getResume() {
    return resume;
  }

  public void setResume(String resume) {
    this.resume = resume;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public List<String> getRoles() {
    return roles;
  }

  public Memory getMemory() {
    return memory;
  }

  public FileDB getFileDB() {
    return fileDB;
  }

  public void setFileDB(FileDB fileDB) {
    this.fileDB = fileDB;
  }
}
