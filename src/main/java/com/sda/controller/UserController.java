package com.sda.controller;

import com.sda.model.Event;
import com.sda.model.Role;
import com.sda.model.User;
import com.sda.service.EventService;
import com.sda.service.RoleService;
import com.sda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    private EventService eventService;

    private RoleService roleService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String homePage(Model model) {
        List<Event> events = eventService.getAllEvents();
        events.sort(Comparator.comparing(Event::getStartDate));
        model.addAttribute("events", events);
        return "index";
    }

    @GetMapping("/login")
    public String signin(Model model, User user) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);
        model.addAttribute("user", user);
        return "signin";
    }

    @PostMapping("/login")
    public String login(HttpSession session, @RequestParam("username") String username){
        session.setAttribute("username", username);
        return "signin";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "signup";
    }

    @PostMapping("/submit")
    public String submit(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) throws Exception {
        User userFoundByName = userService.findUserByUsername(user.getUsername());

        if (userFoundByName != null) {
            result.rejectValue("username", null, "Username already in use");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "signup";
        }

        userService.saveUser(user);

        return "redirect:/login";
    }
}
