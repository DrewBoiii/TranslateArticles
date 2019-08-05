package translatearticles.services;

import translatearticles.persistence.model.Article;

import java.util.List;

public interface ArticleServiceRepository {

    void save(Article article);
    void delete(Long id);
    List<Article> getAll();

}
