package translatearticles.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import translatearticles.persistence.model.User;
import translatearticles.persistence.registration.RegistrationForm;
import translatearticles.services.dao.UserDetailsServiceRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private UserDetailsServiceRepository userDetailsServiceRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController(UserDetailsServiceRepository userDetailsServiceRepository, PasswordEncoder passwordEncoder) {
        this.userDetailsServiceRepository = userDetailsServiceRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/signin")
    public String signInPage(){
        return "signin";
    }

    @GetMapping("/signup")
    public String signUpPage(){
        return "signup";
    }

    @PostMapping("/signup")
    public String processRegistration(RegistrationForm form, Errors errors){
        @Valid User user = form.toUser(passwordEncoder);

        if(errors.hasErrors()){
            return "signup";
        }

        userDetailsServiceRepository.save(user);

        return "redirect:/register/signin";
    }

}
