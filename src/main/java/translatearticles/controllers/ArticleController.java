package translatearticles.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import translatearticles.persistence.model.Article;
import translatearticles.services.ArticleServiceRepository;

@Controller
@RequestMapping("/home")
public class ArticleController {

    private ArticleServiceRepository articleService;

    @Autowired
    public ArticleController(ArticleServiceRepository articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public String homePage(Model model){
        model.addAttribute("articles", articleService.getAll());
        return "index";
    }

    @GetMapping("/editor")
    public String editorPage(Model model){
        model.addAttribute("article", new Article());
        return "editor";
    }

    @PostMapping("/editor/submit")
    public String submitArticle(@ModelAttribute Article article){
        articleService.save(article);
        return "redirect:../";
    }

    @DeleteMapping("/editor/delete/{article_id}")
    public String deleteArticle(@PathVariable("article_id") Long id){
        articleService.delete(id);
        return "redirect:../../";
    }

}
