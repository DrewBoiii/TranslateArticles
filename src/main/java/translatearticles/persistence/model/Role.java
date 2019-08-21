package translatearticles.persistence.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    public Role() {}

    public Role(String name) {
        this.name = name;
    }

}
