package com.sda.controller;

import com.sda.model.Event;
import com.sda.model.User;
import com.sda.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class EventController {
    @Autowired
    private EventService eventService;

    @GetMapping("/create-event")
    public String createEvent(Model model) {
        Event event = new Event();
        model.addAttribute("event", event);

        return "create-event";
    }

    @PostMapping("/save")
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
    public String viewEvent(@PathVariable("id") Long id, Model model) {
        Optional<Event> event = eventService.findEventById(id);
        model.addAttribute("event", event);
        return "view-event";
    }

    @GetMapping("/events")
    public String listEvents(Model model) {
        List<Event> events = eventService.getAllEvents();
        model.addAttribute("events", events);
        return "event-list";
    }

    //=============================================================================================================================
    // To look into Luke or Anton. Two mapping to add a user to an event(signup for event) and for a user to resign from the event
    //=============================================================================================================================

    @PostMapping("/events/{id}/signup")
    public String signup(@PathVariable("id") Long eventId, User user) {
        eventService.addUserToEvent(eventId, user);
        return "redirect:/events";
    }

    @PostMapping("/events/{id}/resign")
    public String resign(@PathVariable("id") Long eventId, User user) {
        eventService.removeUserFromEvent(eventId, user);
        return "redirect:/events";
    }
}
