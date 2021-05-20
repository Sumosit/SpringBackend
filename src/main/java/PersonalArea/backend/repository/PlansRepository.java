package PersonalArea.backend.repository;

import PersonalArea.backend.Entity.Groups;
import PersonalArea.backend.Entity.Plans;
import PersonalArea.backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlansRepository extends JpaRepository<Plans, Long> {
    Plans findPlansById(Long plansId);
    List<Plans> findPlansByUsers(User user);
    List<Plans> findPlansByGroups(Groups groups);
    List<Plans> findPlansByUsersAndGroups(User user, Groups groups);
}
