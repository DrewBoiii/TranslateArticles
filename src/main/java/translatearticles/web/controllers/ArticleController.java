package translatearticles.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import translatearticles.persistence.model.Article;
import translatearticles.service.dao.ArticleService;

import javax.validation.Valid;

@Controller
@RequestMapping("/home")
public class ArticleController {

    private ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public String homePage(Model model){
        model.addAttribute("articles", articleService.getAllByMonth());
        return "index";
    }

    @GetMapping("/articles")
    public String articlesPage(Model model){
        model.addAttribute("allArticles", articleService.getAll());
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

        articleService.save(article);

        return "redirect:../";
    }

    @RequestMapping("/editor/delete/{article_id}")
    public String deleteArticle(@PathVariable("article_id") Long id){
        articleService.delete(id);
        return "redirect:../../";
    }

}
