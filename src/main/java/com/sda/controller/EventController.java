package com.sda.controller;

import com.sda.model.Event;
import com.sda.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/event")
public class EventController {
    @Autowired
    private EventService eventService;

    @PostMapping("/create")
    public String createEvent(@RequestParam("title") String title,
                              @RequestParam("date") LocalDate date,
                              @RequestParam("description") String description) {
        Event event = new Event(title, date, description);
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
