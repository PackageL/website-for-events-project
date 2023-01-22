package com.sda.controller;

import com.sda.model.Comment;
import com.sda.model.Event;
import com.sda.model.User;
import com.sda.service.CommentService;
import com.sda.service.EventService;
import com.sda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/event")
public class EventController {
    @Autowired
    private EventService eventService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/create")
    public String createEvent(Model model) {
        model.addAttribute("event", new Event());
        return "create-event";
    }

    @PostMapping("/create")
    public String saveEvent(@Valid @ModelAttribute("event") Event event,
                            BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("event", event);
            return "create-event";
        }
        eventService.createEvent(event);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String viewEvent(@PathVariable Long id, Model model, Authentication authentication) {
        Optional<Event> event = eventService.findEventById(id);
        User user = userService.findUserByUsername(authentication.getName());
        List<Comment> comments = commentService.getCommentsByEventId(id);
        List<User> attendees = eventService.getAttendeesByEventId(id);
        model.addAttribute("event", event.get());
        model.addAttribute("user", user);
        model.addAttribute("comments", comments);
        model.addAttribute("attendees", attendees);
        model.addAttribute("isSignedUp", attendees.contains(user));
        return "view-event";
    }

    @PostMapping("/{id}/comments")
    public String addComment(@PathVariable Long id, @RequestParam String comment, Authentication authentication) {
        Optional<Event> event = eventService.findEventById(id);
        User user = userService.findUserByUsername(authentication.getName());
        Comment newComment = new Comment(event.orElseGet(Event::new), user, comment);
        commentService.addComment(newComment);
        return "redirect:/event/{id}";
    }

    @GetMapping("/{id}/signup")
    public String signupForEvent(@PathVariable Long id, Authentication authentication) {
        Event event = eventService.findEventById(id).orElseThrow(() -> new IllegalArgumentException("Invalid event id"));
        User user = userService.findUserByUsername(authentication.getName());
        eventService.signupForEvent(event.getId(), user);
        return "redirect:/event/{id}";
    }

    @GetMapping("/{id}/resign")
    public String resignFromEvent(@PathVariable Long id, Authentication authentication) {
        Event event = eventService.findEventById(id).orElseThrow(() -> new IllegalArgumentException("Invalid event id"));
        User user = userService.findUserByUsername(authentication.getName());
        eventService.resignFromEvent(event.getId(), user.getUsername());
        return "redirect:/event/{id}";
    }

    @GetMapping("/search")
    public String searchByTitle(@RequestParam("title") String title, Model model) {
        List<Event> events = eventService.findEventsByTitle(title);
        model.addAttribute("events", events);
        return "search";
    }
}


