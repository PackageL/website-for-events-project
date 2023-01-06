package com.sda.controller;

import com.sda.model.User;
import com.sda.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private UserRepository repository;

    @Autowired
    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public String homePage() {
        return "index";
    }

    @GetMapping("/signin")
    public String signin() {
        return "signin";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "signup";
    }

    @PostMapping("/submit")
    public String submit(@ModelAttribute User user) {

        return "redirect:/";
    }


}
