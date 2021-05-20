package PersonalArea.backend.repository;

import PersonalArea.backend.Entity.Groups;
import PersonalArea.backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupsRepository extends JpaRepository<Groups, Long> {
    Groups findGroupsById(Long groupId);
    List<Groups> findGroupsByUsers(User user);
}
