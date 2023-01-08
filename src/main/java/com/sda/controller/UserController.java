package com.sda.controller;

import com.sda.model.User;
import com.sda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

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
    public String submit(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        User userFoundByEmail = userService.findUserByEmail(user);
        User userFoundByName = userService.findUserByUsername(user);

        if(userFoundByEmail != null) {
            result.rejectValue("email", null, "Email already in user");
        }

        if(userFoundByName != null) {
            result.rejectValue("username", null, "Username already in use");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "signup";
        }

        userService.saveUser(user);
        return "redirect:/";
    }
}
