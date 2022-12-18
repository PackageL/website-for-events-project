package com.sda.controller;

import com.sda.repository.EventRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

    private EventRepository repository;

    public EventController(EventRepository repository) {
        this.repository = repository;
    }
}
