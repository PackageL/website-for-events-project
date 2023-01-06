package com.sda.controller;

import com.sda.model.Event;
import com.sda.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
public class EventController {

    private EventRepository repository;

    @Autowired
    public EventController(EventRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/createAnEvent")
    public String createEvent(@RequestParam("title") String title,
                              @RequestParam("date") LocalDate date,
                              @RequestParam("description") String description) {
        Event event = new Event(title, date, description);
        repository.save(event);
        return "create-event";
    }

    @GetMapping("/events/{id}")
    public String viewEvent(@PathVariable("id") int id, Model model) {
        Optional<Event> event = repository.findById(id);
        model.addAttribute("event", event);
        return "viewEvent";
    }

    @GetMapping("/events")
    public String listEvents(Model model) {
        List<Event> events = repository.findAll();
        model.addAttribute("events", events);
        return "event-list";
    }
}
