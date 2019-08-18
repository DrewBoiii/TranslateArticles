package translatearticles.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import translatearticles.persistence.model.User;
import translatearticles.services.dao.SecurityService;
import translatearticles.services.dao.UserService;

import javax.validation.Valid;

@Slf4j
@Controller
public class UserController {

    private UserService userService;
    private SecurityService securityService;

    @Autowired
    public UserController(UserService userService, SecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }

    @GetMapping("/login")
    public String signInPage(Model model, String logout){
//        if(errors.hasErrors()){
//            return "login";
//        }

//        if(logout != null){
//            model.addAttribute("message", "You have been logged out");
//        }

        return "login";
    }

    @GetMapping("/registration")
    public String signUpPage(Model model){
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String processRegistration(@ModelAttribute("userForm") @Valid User user, Errors errors){
        if(errors.hasErrors()){
            return "registration";
        }

        userService.save(user);

        // TODO: 17.08.2019 getPasswordConfirm()
        securityService.autoLogin(user.getUsername(), user.getPassword());

        return "redirect:/home";
    }

}
