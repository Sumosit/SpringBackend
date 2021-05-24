package PersonalArea.backend.controller;

import PersonalArea.backend.Entity.Groups;
import PersonalArea.backend.repository.GroupsRepository;
import PersonalArea.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("api")
public class GroupsController {

    @Autowired
    GroupsRepository groupsRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/admin/group/create")
    public void createGroup(
            @RequestParam String name) {
        groupsRepository.save(
                new Groups(null, name, null));
    }

    @PostMapping("/admin/group/add/users")
    public void addUsersToGroup(
            @RequestParam Long groupId,
            @RequestParam Long[] usersId
    ) {
        Groups group = groupsRepository.findGroupsById(groupId);
        for (Long user : usersId) {
            group.getUsers().add(userRepository.findUserById(user));
        }
        groupsRepository.save(group);
    }

    @PostMapping("/admin/group/delete/user")
    public void deleteUsersFromGroup(
            @RequestParam Long groupId,
            @RequestParam Long userId
    ) {
        Groups group = groupsRepository.findGroupsById(groupId);
        group.getUsers().remove(userRepository.findUserById(userId));
        groupsRepository.save(group);
    }

    @PostMapping("/admin/group/change/name")
    public void changeGroupName(
            @RequestParam Long groupId,
            @RequestParam String name
    ) {
        Groups group = groupsRepository.findGroupsById(groupId);
        group.setName(name);
        groupsRepository.save(group);
    }

    @GetMapping("/user/group/all")
    public List<Groups> allGroups() {
        return groupsRepository.findAll();
    }

    @GetMapping("/user/group/all/{userId}")
    public List<Groups> allGroupsByUserId(
            @PathVariable Long userId
    ) {
        return groupsRepository.findGroupsByUsers(userRepository.findUserById(userId));
    }

    @GetMapping("/group/one/{groupId}")
    public Groups OneGroup(
            @PathVariable Long groupId
    ) {
        return groupsRepository.findGroupsById(groupId);
    }
}
