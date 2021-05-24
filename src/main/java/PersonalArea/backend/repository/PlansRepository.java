package PersonalArea.backend.repository;

import PersonalArea.backend.Entity.Groups;
import PersonalArea.backend.Entity.Plan;
import PersonalArea.backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlansRepository extends JpaRepository<Plan, Long> {
    Plan findPlanById(Long plansId);
    List<Plan> findPlanByUsers(User user);
    List<Plan> findPlanByGroups(Groups groups);
    List<Plan> findPlanByUsersAndGroups(User user, Groups groups);
}
