package PersonalArea.backend.repository;

import PersonalArea.backend.Entity.Lessons;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface LessonsRepository extends JpaRepository<Lessons, Long> {
  void deleteAllById(Long lessonId);
}
