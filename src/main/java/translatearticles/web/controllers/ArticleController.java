package translatearticles.web.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import translatearticles.persistence.model.Article;
import translatearticles.service.dao.ArticleService;
import translatearticles.service.dao.UserService;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/home")
public class ArticleController {

    private ArticleService articleService;
    private UserService userService;

    @Autowired
    public ArticleController(ArticleService articleService, UserService userService) {
        this.articleService = articleService;
        this.userService = userService;
    }

    @GetMapping
    public String homePage(Model model){
        model.addAttribute("articles", articleService.getAllByMonth());
        return "index";
    }

    @GetMapping("/articles")
    public String articlesPage(Model model, @AuthenticationPrincipal User user){
        translatearticles.persistence.model.User customUser = userService.findByEmail(user.getUsername());

        model.addAttribute("allArticles", articleService.getArticlesByUser(customUser));

        return "articles";
    }

    @GetMapping("/editor")
    public String editorPage(Model model){
        model.addAttribute("article", new Article());
        return "editor";
    }

    @PostMapping("/editor/submit")
    public String submitArticle(@ModelAttribute @Valid Article article, Errors articleBlank,
                                @AuthenticationPrincipal User user){
        if(articleBlank.hasErrors()){
            return "editor";
        }

        log.info("Authenticated user: " + user.toString());

        translatearticles.persistence.model.User customUser = userService.findByEmail(user.getUsername());

        article.setUser(customUser);

        articleService.save(article);

        return "redirect:../";
    }

    @RequestMapping("/editor/delete/{article_id}")
    public String deleteArticle(@PathVariable("article_id") Long id){
        articleService.delete(id);
        return "redirect:/home/articles";
    }

}
