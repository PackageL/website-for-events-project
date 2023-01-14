package com.sda.service.impl;

import com.sda.model.Event;
import com.sda.model.User;
import com.sda.repository.EventRepository;
import com.sda.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

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
    public void addUserToEvent(Long eventId, User user) {
        Event event = eventRepository.findById(eventId).orElseThrow(() ->
                new EntityNotFoundException("event with id: " + eventId + " was not found"));
        event.getParticipants().add(user);
        eventRepository.save(event);
    }

    @Override
    public void removeUserFromEvent(Long eventId, User user) {
        Event event = eventRepository.findById(eventId).orElseThrow(() ->
                new EntityNotFoundException("event with id: " + eventId + " was not found"));
        event.getParticipants().remove(user);
        eventRepository.save(event);
    }
}
