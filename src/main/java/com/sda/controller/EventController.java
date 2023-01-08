package com.sda.controller;

import com.sda.model.Event;
import com.sda.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    public String saveEvent(@RequestParam("title") String title,
                            @RequestParam("date") LocalDate date,
                            @RequestParam("description") String description,
                            BindingResult result, Model model) {
        Event event = new Event(title, date, description);
        if (result.hasErrors()) {
            model.addAttribute("event", event);
            return "create-event";
        }
        eventService.createEvent(event);
        return "create-event";
    }

    @GetMapping("/{id}")
    public String viewEvent(@PathVariable("id") Long id, Model model) {
        Optional<Event> event = eventService.findEventById(id);
        model.addAttribute("event", event);
        return "view-event";
    }

    @GetMapping
    public String listEvents(Model model) {
        List<Event> events = eventService.getAllEvents();
        model.addAttribute("events", events);
        return "event-list";
    }
}
