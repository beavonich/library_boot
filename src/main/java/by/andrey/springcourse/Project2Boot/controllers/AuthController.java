package by.andrey.springcourse.Project2Boot.controllers;

import by.andrey.springcourse.Project2Boot.models.Userr;
import by.andrey.springcourse.Project2Boot.services.RegistrationService;
import by.andrey.springcourse.Project2Boot.util.UserrValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final UserrValidator userrValidator;
    private final RegistrationService registrationService;

    @Autowired
    public AuthController(UserrValidator userrValidator, RegistrationService registrationService) {
        this.userrValidator = userrValidator;
        this.registrationService = registrationService;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("user") Userr user){
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("user") @Valid Userr user, BindingResult bindingResult){
        userrValidator.validate(user, bindingResult);

        if(bindingResult.hasErrors())
            return "auth/registration";

        registrationService.register(user);

        return "redirect:/auth/login";
    }
}
