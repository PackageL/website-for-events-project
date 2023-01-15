package com.sda.controller;

import com.sda.model.Role;
import com.sda.model.User;
import com.sda.service.RoleService;
import com.sda.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {
    private final UserService userService;

    private RoleService roleService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String homePage() {
        return "index";
    }

    @GetMapping("/shit")
    public String shitPage() {
        return "shit";
    }

    @GetMapping("/login")
    public String signin(Model model, User user) {

        model.addAttribute("user", user);
        return "signin";
    }

    @PostMapping("/login")
    public String login(){

        return "signin";
    }

    //=============================================================================================================================
    // To look into Luke or Anton. Check if user exists when logging in
    //=============================================================================================================================

//    @PostMapping("/signin")
//    public String signin(@RequestParam("username") String username,
//                         @RequestParam("password") String password,
//                         Model model) {
//        // Check if the user exists and sign them in
//        User user = userService.signin(username, password);
//        if (user != null) {
//            // Sign in the user
//            session.setAttribute("user", user);
//            return "redirect:/";
//        } else {
//            // Display an error message
//            model.addAttribute("error", "Invalid username or password.");
//            return "signin";
//        }
//    }


    @GetMapping("/signup")
    public String signup(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "signup";
    }

    @PostMapping("/submit")
    public String submit(@Valid @ModelAttribute("user") User user, BindingResult result, Role role, Model model) throws Exception {
        User userFoundByName = userService.findUserByUsername(user.getUsername());

        if (userFoundByName != null) {
            result.rejectValue("username", null, "Username already in use");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "signup";
        }

        role.setName("ROLE_ADMIN");
        userService.saveUser(user);

        return "redirect:/";
    }
}
