package com.raphmei.eventplanner.service;

import com.raphmei.eventplanner.dto.EventDTO;
import com.raphmei.eventplanner.entity.Event;

import java.util.List;

public interface EventService {
    EventDTO createEvent(Event event);
    List<EventDTO> getAllEvents();
    EventDTO getEventById(Long id);
    void deleteEventById(Long id);
}
