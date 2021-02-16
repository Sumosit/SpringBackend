package PersonalArea.backend.repository;

import java.util.Optional;

import PersonalArea.backend.Entity.ERole;
import PersonalArea.backend.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
