package PersonalArea.backend.repository;

import PersonalArea.backend.Entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaryRepository extends JpaRepository<Salary, Long> {
}
