package PersonalArea.backend.Entity;

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
@Table(	name = "users",
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
  @Size(max = 50)
  @Email
  private String email;

  @OneToOne
  private PersonalData personalData;
  @OneToMany
  private Set<Education> education;
  @OneToMany
  private Set<Training> training;
  @OneToMany
  private Set<Lessons> lessons;
  @OneToMany
  private Set<Notes> reminders;
  @Column(length = 1000)
  @Value(" ")
  private String resume;

  @OneToMany
  private Set<Salary> salaries;

  @OneToOne
  private FileDB fileDB;

  @NotBlank
  @Size(max = 120)
  private String password;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(	name = "user_roles",
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

  public User(Long id, @NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 50) @Email String email, Set<Salary> salaries, FileDB fileDB, @NotBlank @Size(max = 120) String password, Set<Role> roles) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.salaries = salaries;
    this.fileDB = fileDB;
    this.password = password;
    this.roles = roles;
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
