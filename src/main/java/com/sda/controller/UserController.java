package com.sda.controller;

import com.sda.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping("/eventList")
    public String eventList() {
        return "event-list";
    }

    @GetMapping("/signin")
    public String signin() {
        return "signin";
    }

}
