package PersonalArea.backend.Entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Task {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  @Column(length = 1000)
  private String description;
  private Timestamp sendDate;
  private int deadline_day, deadline_month, deadline_year,
  deadline_hours, deadline_minute, deadline_seconds;
  @OneToMany
  private Set<FileDB> fileDBSet;
  @OneToOne
  private User author;
}
