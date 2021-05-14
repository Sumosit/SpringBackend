package PersonalArea.backend.repository;

import PersonalArea.backend.Entity.Salary;
import PersonalArea.backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalaryRepository extends JpaRepository<Salary, Long> {
  List<Salary> findAllByUser(User user);
}
