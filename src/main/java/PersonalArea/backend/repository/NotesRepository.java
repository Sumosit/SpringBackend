package PersonalArea.backend.repository;

import PersonalArea.backend.Entity.Notes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotesRepository extends JpaRepository<Notes, Long> {
  List<Notes> findAllByYearAndMonthAndDay(int year, int month, int day);
  boolean existsAllByYearAndMonthAndDay(int year, int month, int day);
}
