package translatearticles.persistence.dao;

import org.springframework.data.repository.CrudRepository;
import translatearticles.persistence.model.Article;

import java.util.List;

public interface ArticleRepository extends CrudRepository<Article, Long> {

    List<Article> findByTitle(String title);
    Article findArticleById(Long id);
    void deleteById(Integer id);

}
