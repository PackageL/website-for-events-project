package com.sda.service;

import com.sda.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    private EventRepository repository;

    @Autowired
    public EventService(EventRepository repository) {
        this.repository = repository;
    }
}
