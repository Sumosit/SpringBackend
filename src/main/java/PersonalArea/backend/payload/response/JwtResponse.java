package PersonalArea.backend.payload.response;

import PersonalArea.backend.models.FileDB;
import PersonalArea.backend.models.Salary;

import java.util.List;
import java.util.Set;

public class JwtResponse {
  private String token;
  private String type = "Bearer";
  private Long id;
  private String username;
  private String email;
  private FileDB fileDB;
  private Set<Salary> salaries;
  private List<String> roles;

  public JwtResponse(String accessToken, Long id, String username, String email, FileDB fileDB, Set<Salary> salaries, List<String> roles) {
    this.token = accessToken;
    this.id = id;
    this.username = username;
    this.email = email;
    this.fileDB = fileDB;
    this.salaries = salaries;
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

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public List<String> getRoles() {
    return roles;
  }

  public Set<Salary> getSalaries() {
    return salaries;
  }

  public void setSalaries(Set<Salary> salaries) {
    this.salaries = salaries;
  }

  public FileDB getFileDB() {
    return fileDB;
  }

  public void setFileDB(FileDB fileDB) {
    this.fileDB = fileDB;
  }
}