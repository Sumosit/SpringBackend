package PersonalArea.backend.Entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
@Data
@Entity
public class Task {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;
  private String description;
  private Timestamp sendDate;
  private Timestamp deadline;
  @OneToMany
  private Set<FileDB> fileDBSet;
  @OneToOne
  private User author;
}
