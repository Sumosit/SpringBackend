package PersonalArea.backend.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "users",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
    })
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(max = 20)
  private String username;

  @NotBlank
  @Size(max = 30)
  private String name;
  @NotBlank
  @Size(max = 30)
  private String surname;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  @JsonIgnore
  @OneToOne(fetch = FetchType.EAGER)
  private UserExtra userExtra;
  @JsonIgnore
  @OneToOne(fetch = FetchType.EAGER)
  private Memory memory;
  @JsonIgnore
  @OneToMany(fetch = FetchType.EAGER)
  private Set<Notes> reminders;
  @Column(length = 1000)
  @JsonIgnore
  @ManyToMany(fetch = FetchType.EAGER)
  private Set<Task> tasks;
  @Column(length = 1000)
  @Value(" ")
  private String resume;

  @OneToOne
  private FileDB fileDB;

  @NotBlank
  @Size(max = 120)
  private String password;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "user_roles",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

  public User() {
  }

  public User(String username, String email, String password) {
    this.username = username;
    this.email = email;
    this.password = password;
  }

  public User(Long userid) {
    this.id = userid;
  }

  public User(
      Long id,
      @NotBlank @Size(max = 20)
          String username,
      @NotBlank @Size(max = 50)
      @Email String email,
      FileDB fileDB,
      @NotBlank @Size(max = 120) String password,
      Set<Role> roles) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.fileDB = fileDB;
    this.password = password;
    this.roles = roles;
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

  public Memory getMemory() {
    return memory;
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

  public String getResume() {
    return resume;
  }

  public void setResume(String resume) {
    this.resume = resume;
  }

  public FileDB getFileDB() {
    return fileDB;
  }

  public void setFileDB(FileDB fileDB) {
    this.fileDB = fileDB;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }
}
