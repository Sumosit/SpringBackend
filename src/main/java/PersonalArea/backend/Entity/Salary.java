package PersonalArea.backend.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Salary {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  User user;
  @OneToOne
  User author;
  Timestamp period;
  int days, hours;
  String month;
  double salary, opv, oneMzp, vosms, total_nalog,
      ipn, withheld, debt;
}
