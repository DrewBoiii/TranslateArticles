package translatearticles.persistence.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private final String name;

    @ManyToMany(mappedBy = "roles")
    private final List<User> users;

}
