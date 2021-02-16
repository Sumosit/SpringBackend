package PersonalArea.backend.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Salary {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private double amount;
  private String date;
}
