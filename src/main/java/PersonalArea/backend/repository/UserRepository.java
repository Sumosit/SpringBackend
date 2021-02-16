package PersonalArea.backend.repository;

import java.util.Optional;

import PersonalArea.backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    User findByEmail(String email);
    User findUserById(Long user_id);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}

