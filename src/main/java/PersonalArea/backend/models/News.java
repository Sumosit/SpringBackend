package PersonalArea.backend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class News {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY  )
  private Long id;
  private Long authorId;
  private String authorUsername;
  private String authorAvatar;
  @Column(length = 1000)
  private String title;
  @Column(length = 3000)
  private String content;
  private Timestamp date;
}