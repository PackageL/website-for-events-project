package com.sda.service.impl;

import com.sda.model.Event;
import com.sda.model.User;
import com.sda.repository.EventRepository;
import com.sda.service.EventService;
import com.sda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    UserService userService;

    @Override
    public void createEvent(Event event) {
        eventRepository.save(event);
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Optional<Event> findEventById(Long id) {
        return eventRepository.findById(id);
    }

    @Override
    public void signupForEvent(Long eventId, String username) {
        Event event = eventRepository.findEventById(eventId);
        User user = userService.findUserByUsername(username);
        event.addAttendee(user);
        eventRepository.save(event);
    }

    public void resignFromEvent(Long eventId, String username) {
        Event event = eventRepository.findEventById(eventId);
        User user = userService.findUserByUsername(username);
        event.removeAttendee(user);
        eventRepository.save(event);
    }
}
