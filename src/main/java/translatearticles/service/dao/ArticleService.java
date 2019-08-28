package translatearticles.service.dao;

import translatearticles.persistence.model.Article;
import translatearticles.persistence.model.User;

import java.util.List;

public interface ArticleService {

    void save(Article article);
    void delete(Long id);
    List<Article> getAll();
    List<Article> getAllByMonth();
    List<Article> getArticlesByUser(User user);

}
