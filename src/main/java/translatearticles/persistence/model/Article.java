package translatearticles.persistence.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
public class Article implements Serializable, Comparable<Article> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @NotNull
    private String title;

    @Column(length = 1000000)
    @Lob
    @NotNull
    private String content;

    private Date createdAt;

    @PrePersist
    void createdAt(){
        this.createdAt = new Date();
    }

    @Override
    public int compareTo(Article o) {
        return Long.compare(this.createdAt.getTime(), o.createdAt.getTime());
    }
}
