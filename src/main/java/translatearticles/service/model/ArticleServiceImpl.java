package translatearticles.service.model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import translatearticles.persistence.dao.ArticleRepository;
import translatearticles.persistence.model.Article;
import translatearticles.service.dao.ArticleService;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository repository;

    @Autowired
    public ArticleServiceImpl(ArticleRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Article article){
        Article savedArticle = repository.save(article);
        log.info("Saved Article: " + savedArticle.toString());
    }

    @Override
    public void delete(Long id) {
        Article articleToDelete = repository.findArticleById(id);
        repository.deleteById(id);
        log.info("Deleted Article:" + articleToDelete.toString());
    }

    @Override
    public List<Article> getAll(){
        return StreamSupport
                .stream(Spliterators.spliteratorUnknownSize(repository.findAll().iterator(), Spliterator.NONNULL), false)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    @Override
    public List<Article> getAllByMonth() {
        Date dateMonthAgo = new Date();
        int month = dateMonthAgo.getMonth();
        dateMonthAgo.setMonth(month - 1);
        log.info("Date is " + dateMonthAgo.toString());
        return StreamSupport
                .stream(Spliterators.spliteratorUnknownSize(repository.findArticlesByCreatedAtIsAfter(dateMonthAgo).iterator(), Spliterator.NONNULL), false)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }
}
