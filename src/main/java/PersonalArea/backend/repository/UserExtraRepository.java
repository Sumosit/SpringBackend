package PersonalArea.backend.repository;

import PersonalArea.backend.Entity.UserExtra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserExtraRepository extends JpaRepository<UserExtra, Long> {
  UserExtra findUserExtraById(Long userExtra_id);
}
