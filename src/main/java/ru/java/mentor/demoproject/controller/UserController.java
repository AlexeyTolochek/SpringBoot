package ru.java.mentor.demoproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.java.mentor.demoproject.service.UserService;

@Controller
public class UserController {

    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/user")
    public String hello(){
        return "userPage";
    }

    @GetMapping("/access_denied")
    public String accessDenied() {
        return "onlyAdmin";
    }
}
