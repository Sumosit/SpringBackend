package PersonalArea.backend.controller;

import PersonalArea.backend.Entity.*;
import PersonalArea.backend.repository.GroupsRepository;
import PersonalArea.backend.repository.PlansRepository;
import PersonalArea.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
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

    @MessageMapping("/plans/{planId}")
    @SendTo("/topic/plans/{planId}")
    public int webSocketPlans(int signalToUpdate) throws Exception {
        Thread.sleep(1000);
        return signalToUpdate;
    }

    @PostMapping("/admin/plans/create")
    public void createPlan(@RequestParam String name) {
        plansRepository.save(new Plan(null, name, 0, null, null, null));
    }

    @PostMapping("/admin/plans/add/users")
    public void addUsersToPlan(
            @RequestParam Long plansId,
            @RequestParam Long[] usersId
    ) {
        Plan plan = plansRepository.findPlanById(plansId);
        for (Long user : usersId) {
            plan.getUsers().add(userRepository.findUserById(user));
        }
        plansRepository.save(plan);
    }

    @PostMapping("/admin/plans/add/groups")
    public void addGroupsToPlan(
            @RequestParam Long plansId,
            @RequestParam Long[] groupsId
    ) {
        Plan plan = plansRepository.findPlanById(plansId);
        for (Long group : groupsId) {
            plan.getGroups().add(groupsRepository.findGroupsById(group));
        }
        plansRepository.save(plan);
    }


    @PostMapping("/admin/plans/add/users/groups")
    public void addUsersAndGroupsToPlan(
            @RequestParam Long plansId,
            @RequestParam Long[] usersId,
            @RequestParam Long[] groupsId
    ) {
        Plan plan = plansRepository.findPlanById(plansId);
        for (Long user : usersId) {
            plan.getUsers().add(userRepository.findUserById(user));
        }
        for (Long group : groupsId) {
            plan.getGroups().add(groupsRepository.findGroupsById(group));
            for (User user : groupsRepository.findGroupsById(group).getUsers()) {
                plan.getUsers().add(user);
            }
        }
        plansRepository.save(plan);
    }

    @PostMapping("/admin/plans/add/plan")
    public void addPlanToPlan(
            @RequestParam Long planId,
            @RequestParam String newPlanName
    ) {
        Plan plan = plansRepository.findPlanById(planId);
        Plan newPlan = new Plan(null, newPlanName, 0, null, null, null);
        plansRepository.save(newPlan);
        plan.getPlans().add(newPlan);
        plansRepository.save(plan);
    }

    @GetMapping("/user/plans/all")
    public List<Plan> getAllPlans() {
        return plansRepository.findAll();
    }

    @GetMapping("/user/plans/all/{userId}")
    public List<Plan> allPlansByUserId(
            @PathVariable Long userId
    ) {
        return plansRepository.findPlanByUsers(userRepository.findUserById(userId));
    }

    @GetMapping("/user/plans/one/{planId}")
    public Plan OnePlan(
            @PathVariable Long planId
    ) {
        return plansRepository.findPlanById(planId);
    }

    @PostMapping("/admin/plans/change/completed")
    public void changePlanStatus(
            @RequestParam Long planId,
            @RequestParam int completed,
            @RequestParam Long[] rootPlan
    ) {
        Plan plan = plansRepository.findPlanById(planId);
        plan.setCompleted(completed);
        plansRepository.save(plan);
        for (Long rootPlanIds : rootPlan) {
            Plan rPlan = plansRepository.findPlanById(rootPlanIds);
            completed = 0;
            if (rPlan.getPlans().size() > 0) {
                for (Plan rp : rPlan.getPlans()) {
                    completed = completed + rp.getCompleted();
                }
                rPlan.setCompleted(completed / rPlan.getPlans().size());
            }

            plansRepository.save(rPlan);
        }
    }

    @PostMapping("/admin/plans/change/completed/calculate")
    public void changePlanStatus(
            @RequestParam Long[] rootPlan
    ) {
        for (Long rootPlanIds : rootPlan) {
            Plan rPlan = plansRepository.findPlanById(rootPlanIds);
            int completed = 0;
            if (rPlan.getPlans().size() > 0) {
                for (Plan rp : rPlan.getPlans()) {
                    completed = completed + rp.getCompleted();
                }
                rPlan.setCompleted(completed / rPlan.getPlans().size());
            }

            plansRepository.save(rPlan);
        }
    }

    @PostMapping("/admin/plans/change/name")
    public void changePlanName(
            @RequestParam Long planId,
            @RequestParam String name
    ) {
        Plan plan = plansRepository.findPlanById(planId);
        plan.setName(name);
        plansRepository.save(plan);
    }

    @PostMapping("/admin/plans/delete/user")
    public void deleteUserFromPlan(
            @RequestParam Long planId,
            @RequestParam Long userId
    ) {
        Plan plan = plansRepository.findPlanById(planId);
        plan.getUsers().remove(userRepository.findUserById(userId));
        plansRepository.save(plan);
    }

    @PostMapping("/admin/plans/delete/group")
    public void deleteGroupFromPlan(
            @RequestParam Long planId,
            @RequestParam Long groupId
    ) {
        Plan plan = plansRepository.findPlanById(planId);
        Groups group = groupsRepository.findGroupsById(groupId);
        plan.getUsers().removeAll(group.getUsers());
        plan.getGroups().remove(groupsRepository.findGroupsById(groupId));
        plansRepository.save(plan);
    }

}
