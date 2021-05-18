package PersonalArea.backend.Entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class UserExtra {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String surname;
  private String iin;
  @OneToMany
  private Set<Document> documents;
//  @OneToOne
//  private FileDB passport;
//  @OneToOne
//  private FileDB diploma;
//  @OneToOne
//  private FileDB snils;
//  @OneToOne
//  private FileDB medicalVerification;
//  @OneToOne
//  private FileDB conviction;
//  @OneToOne
//  private FileDB employmentContract;
//  @OneToOne
//  private FileDB employmentHistory;
}
