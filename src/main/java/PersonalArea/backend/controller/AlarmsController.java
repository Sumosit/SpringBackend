package PersonalArea.backend.controller;

import PersonalArea.backend.Entity.Alarms;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class AlarmsController {
    @MessageMapping("/alarms")
    @SendTo("/topic/alarms")
    public Alarms alarms(Alarms alarms) throws Exception {
        Thread.sleep(1000);
        try {
            return alarms;
        } catch (Exception e) {
            return null;
        }
    }
}
