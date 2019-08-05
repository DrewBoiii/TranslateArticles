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

    // TODO: 05.08.2019 find out the purpose of the @Lob annotation 
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
    public int compareTo(Article article) {
        return Long.compare(this.createdAt.getTime(), article.createdAt.getTime());
    }
}
