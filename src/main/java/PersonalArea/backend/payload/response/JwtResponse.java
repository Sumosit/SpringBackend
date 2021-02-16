package PersonalArea.backend.payload.response;

import PersonalArea.backend.Entity.*;

import java.util.List;
import java.util.Set;

public class JwtResponse {
  private String token;
  private String type = "Bearer";
  private Long id;
  private String username;
  private String email;
  private String resume;
  private FileDB fileDB;
  private PersonalData personalData;
  private Set<Education> educationSet;
  private Set<Training> trainingSet;
  private Set<Lessons> lessonsSet;
  private Set<Salary> salaries;
  private List<String> roles;

  public JwtResponse(String accessToken,
                     Long id,
                     String username,
                     String email,
                     String resume,
                     FileDB fileDB,
                     PersonalData personalData,
                     Set<Education> educationSet,
                     Set<Training> trainingSet,
                     Set<Lessons> lessonsSet,
                     Set<Salary> salaries,
                     List<String> roles) {
    this.token = accessToken;
    this.id = id;
    this.username = username;
    this.email = email;
    this.resume = resume;
    this.fileDB = fileDB;
    this.personalData = personalData;
    this.educationSet = educationSet;
    this.trainingSet = trainingSet;
    this.lessonsSet = lessonsSet;
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

  public void setPersonalData(PersonalData personalData) {
    this.personalData = personalData;
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
