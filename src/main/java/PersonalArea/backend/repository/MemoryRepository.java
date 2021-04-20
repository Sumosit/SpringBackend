package PersonalArea.backend.repository;

import PersonalArea.backend.Entity.Memory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoryRepository extends JpaRepository<Memory, Long> {
  Memory findAllById(Long memoryId);
}
