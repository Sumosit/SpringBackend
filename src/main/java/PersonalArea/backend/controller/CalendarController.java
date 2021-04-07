package PersonalArea.backend.controller;

import PersonalArea.backend.Entity.Notes;
import PersonalArea.backend.Entity.User;
import PersonalArea.backend.repository.NotesRepository;
import PersonalArea.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("api/user")
public class CalendarController {

  @Autowired
  NotesRepository notesRepository;

  @Autowired
  UserRepository userRepository;

  @GetMapping("/notes/{userId}/{year}/{month}/{day}")
  public List<Notes> getAllNotesByYearMonthDay(@PathVariable Long userId,
                                           @PathVariable int year,
                                           @PathVariable int month,
                                           @PathVariable int day) {
    try {
      User user = userRepository.getOne(userId);
      List<Notes> total = new ArrayList<>();
      for (Notes notes :user.getReminders()) {
        if (notes.getYear()==year &&
        notes.getMonth()==month &&
        notes.getDay()==day) {
          total.add(notes);
        }
      }
      return total;
    } catch (Exception e) {
      return null;
    }
  }

  @GetMapping("/notes/exists/{userId}")
  public Set<Notes> existsNotesByYearMonthDay(@PathVariable Long userId) {
    try {
      User user = userRepository.getOne(userId);
      return user.getReminders();
    } catch (Exception e) {
      return null;
    }
  }

  @PostMapping("/notes")
  public String setReminder(@RequestParam Long userId,
                            @RequestParam int year,
                            @RequestParam int month,
                            @RequestParam int day,
                            @RequestParam Timestamp date,
                            @RequestParam String title,
                            @RequestParam String description) {
    try {
      Notes notes =
          new Notes(null, year, month, day, date, title, description);
      notesRepository.save(notes);
      User user = userRepository.getOne(userId);
      user.getReminders().add(notes);
      userRepository.save(user);
    } catch (Exception e) {
      return e.toString();
    }
    return null;
  }
}
