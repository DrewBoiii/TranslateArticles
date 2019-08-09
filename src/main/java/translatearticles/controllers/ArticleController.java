package translatearticles.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import translatearticles.persistence.model.Article;
import translatearticles.services.dao.ArticleServiceRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/home")
public class ArticleController {

    private ArticleServiceRepository articleServiceRepository;

    @Autowired
    public ArticleController(ArticleServiceRepository articleServiceRepository) {
        this.articleServiceRepository = articleServiceRepository;
    }

    @GetMapping
    public String homePage(Model model){
        model.addAttribute("articles", articleServiceRepository.getAllByMonth());
        return "index";
    }

    @GetMapping("/articles")
    public String articlesPage(Model model){
        model.addAttribute("allArticles", articleServiceRepository.getAll());
        return "articles";
    }

    @GetMapping("/editor")
    public String editorPage(Model model){
        model.addAttribute("article", new Article());
        return "editor";
    }

    @PostMapping("/editor/submit")
    public String submitArticle(@ModelAttribute @Valid Article article, Errors articleBlank){
        if(articleBlank.hasErrors()){
            return "editor";
        }

        articleServiceRepository.save(article);

        return "redirect:../";
    }

    @RequestMapping("/editor/delete/{article_id}")
    public String deleteArticle(@PathVariable("article_id") Long id){
        articleServiceRepository.delete(id);
        return "redirect:../../";
    }

}
