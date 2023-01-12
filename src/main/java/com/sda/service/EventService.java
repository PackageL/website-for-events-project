package com.sda.service;

import com.sda.model.Event;
import com.sda.model.User;

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
     * @return list of events
     */
    List<Event> getAllEvents();

    /**
     * Find user by id
     * @param id user id
     * @return user
     */
    Optional<Event> findEventById(Long id);

    void addUserToEvent(Long eventId, User user);

    void removeUserFromEvent(Long eventId, User user);
}
