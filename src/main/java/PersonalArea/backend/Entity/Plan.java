package PersonalArea.backend.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int completed;

    @ManyToMany
    private Set<User> users;

    @ManyToMany
    private Set<Groups> groups;

    @OneToMany
    private Set<Plan> plans;
}
