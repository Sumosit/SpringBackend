package PersonalArea.backend.repository;

import PersonalArea.backend.Entity.LessonsTasks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonsTasksRepository extends JpaRepository<LessonsTasks, Long> {
}
