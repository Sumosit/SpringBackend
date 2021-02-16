package PersonalArea.backend.repository;

import PersonalArea.backend.Entity.Training;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingRepository extends JpaRepository<Training, Long> {
}
