package translatearticles.persistence.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
public class Article implements Serializable, Comparable<Article> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @Size(min = 2, max = 100)
    private String title;

    // TODO: 05.08.2019 find out the purpose of the @Lob annotation
    @Column(length = 1000000)
    @Lob
    @NotBlank(message = "Content is required")
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
