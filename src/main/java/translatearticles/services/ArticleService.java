package translatearticles.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import translatearticles.persistence.dao.ArticleRepository;
import translatearticles.persistence.model.Article;

import java.util.Comparator;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class ArticleService {

    private final ArticleRepository repository;

    @Autowired
    public ArticleService(ArticleRepository repository) {
        this.repository = repository;
    }

    public void save(Article article){
        Article savedArticle = repository.save(article);
        log.info("Saved article: " + savedArticle.toString());
    }

    public void delete(Integer id){
        Article articleToDelete = repository.findArticleById(id);
        if(articleToDelete != null){
            repository.deleteById(id);
            log.info("Deleted article:" + articleToDelete.toString());
        }
    }

    public List<Article> getAll(){
        return StreamSupport
                .stream(Spliterators.spliteratorUnknownSize(repository.findAll().iterator(), Spliterator.NONNULL), false)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

}