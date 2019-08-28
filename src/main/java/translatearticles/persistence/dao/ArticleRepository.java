package translatearticles.persistence.dao;

import org.springframework.data.repository.CrudRepository;
import translatearticles.persistence.model.Article;
import translatearticles.persistence.model.User;

import java.util.Date;
import java.util.List;

public interface ArticleRepository extends CrudRepository<Article, Long> {

    Article findArticleById(Long id);
    void deleteById(Long id);
    List<Article> findArticlesByCreatedAtIsAfter(Date date);
    List<Article> findArticlesByUser(User user);

}
