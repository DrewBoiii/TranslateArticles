package translatearticles.persistence.dao;

import org.springframework.data.repository.CrudRepository;
import translatearticles.persistence.model.Article;

import java.util.List;

public interface ArticleRepository extends CrudRepository<Article, Integer> {

    List<Article> findByTitle(String title);
    Article findArticleById(Integer id);
    void deleteById(Integer id);

}
