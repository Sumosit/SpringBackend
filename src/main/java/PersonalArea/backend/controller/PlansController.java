package PersonalArea.backend.controller;

import PersonalArea.backend.Entity.Groups;
import PersonalArea.backend.Entity.Plans;
import PersonalArea.backend.repository.GroupsRepository;
import PersonalArea.backend.repository.PlansRepository;
import PersonalArea.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("api")
public class PlansController {

    @Autowired
    PlansRepository plansRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    GroupsRepository groupsRepository;

    @PostMapping("/admin/plans/create")
    public void createPlan(@RequestParam String name) {
        plansRepository.save(new Plans(null, name, null, null));
    }

    @PostMapping("/admin/plans/add/users")
    public void addUsersToPlan(
            @RequestParam Long plansId,
            @RequestParam Long[] usersId
    ) {
        Plans plans = plansRepository.findPlansById(plansId);
        for (Long user : usersId) {
            plans.getUsers().add(userRepository.findUserById(user));
        }
        plansRepository.save(plans);
    }

    @PostMapping("/admin/plans/add/groups")
    public void addGroupsToPlan(
            @RequestParam Long plansId,
            @RequestParam Long[] groupsId
    ) {
        Plans plans = plansRepository.findPlansById(plansId);
        for (Long group : groupsId) {
            plans.getGroups().add(groupsRepository.findGroupsById(group));
        }
        plansRepository.save(plans);
    }


    @PostMapping("/admin/plans/add/users/groups")
    public void addUsersAndGroupsToPlan(
            @RequestParam Long plansId,
            @RequestParam Long[] usersId,
            @RequestParam Long[] groupsId
    ) {
        Plans plans = plansRepository.findPlansById(plansId);
        for (Long user : usersId) {
            plans.getUsers().add(userRepository.findUserById(user));
        }
        for (Long group : groupsId) {
            plans.getGroups().add(groupsRepository.findGroupsById(group));
        }
        plansRepository.save(plans);
    }

    @GetMapping("/user/plans/all")
    public List<Plans> getAllPlans() {
        return plansRepository.findAll();
    }

    @GetMapping("/user/plans/all/{userId}")
    public List<Plans> allPlansByUserId(
            @PathVariable Long userId
    ) {
        return plansRepository.findPlansByUsers(userRepository.findUserById(userId));
    }

    @GetMapping("/user/plans/one/{planId}")
    public Plans OnePlan(
            @PathVariable Long planId
    ) {
        return plansRepository.findPlansById(planId);
    }

    @PostMapping("/admin/plans/change/name")
    public void changePlanName(
            @RequestParam Long planId,
            @RequestParam String name
    ) {
        Plans plan = plansRepository.findPlansById(planId);
        plan.setName(name);
        plansRepository.save(plan);
    }

    @PostMapping("/admin/plans/delete/user")
    public void deleteUserFromPlan(
            @RequestParam Long planId,
            @RequestParam Long userId
    ) {
        Plans plan = plansRepository.findPlansById(planId);
        plan.getUsers().remove(userRepository.findUserById(userId));
        plansRepository.save(plan);
    }

    @PostMapping("/admin/plans/delete/group")
    public void deleteGroupFromPlan(
            @RequestParam Long planId,
            @RequestParam Long groupId
    ) {
        Plans plan = plansRepository.findPlansById(planId);
        Groups group = groupsRepository.findGroupsById(groupId);
        plan.getUsers().removeAll(group.getUsers());
        plan.getGroups().remove(groupsRepository.findGroupsById(groupId));
        plansRepository.save(plan);
    }

}
