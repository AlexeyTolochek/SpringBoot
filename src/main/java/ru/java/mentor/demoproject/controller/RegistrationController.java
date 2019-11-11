package ru.java.mentor.demoproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.java.mentor.demoproject.domain.User;
import ru.java.mentor.demoproject.service.UserService;

@Controller
public class RegistrationController {

    private UserService service;
    private Environment environment;

    @Autowired
    public RegistrationController(UserService service, Environment environment) {
        this.service = service;
        this.environment = environment;
    }

    @GetMapping("/reg")
    public String reg(@ModelAttribute("message") String message) {
        return "registration";
    }

    @PostMapping("/reg/user")
    public String regUser(@ModelAttribute("user") User user, Model model) {
        if (service.addUser(user)) {
            return "redirect:/";
        } else {
            model.addAttribute("message", environment.getRequiredProperty("invalidData"));
        }
        return "redirect:/reg";
    }
}
