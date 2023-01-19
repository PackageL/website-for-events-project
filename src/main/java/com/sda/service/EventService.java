package com.sda.service;

import com.sda.model.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {
    /**
     * To create a new event
     *
     * @param event Event
     */
    void createEvent(Event event);

    /**
     * To get all events
     *
     * @return list of events
     */
    List<Event> getAllEvents();

    /**
     * Find user by id
     *
     * @param id user id
     * @return user
     */
    Optional<Event> findEventById(Long id);

    void signupForEvent(Long eventId, String username);
    void resignFromEvent(Long eventId, String username);
}
