package PersonalArea.backend.repository;

import java.util.Optional;

import PersonalArea.backend.models.ERole;
import PersonalArea.backend.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}