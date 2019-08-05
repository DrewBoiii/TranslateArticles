package translatearticles.services;

import translatearticles.persistence.model.Article;

import java.util.List;

public interface ArticleServiceRepository {

    void save(Article article);
    void delete(Integer id);
    List<Article> getAll();

}
