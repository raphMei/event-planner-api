package com.raphmei.eventplanner.controller;

import com.raphmei.eventplanner.dto.EventDTO;
import com.raphmei.eventplanner.entity.Event;
import com.raphmei.eventplanner.service.EventService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@EnableMethodSecurity
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public EventDTO createEvent(@RequestBody @Valid Event event) {
        return eventService.createEvent(event);
    }

    @GetMapping()
    public List<EventDTO> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/{id}")
    public EventDTO getEventById(@PathVariable Long id) {
        return eventService.getEventById(id);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteEventById(@PathVariable Long id) {
        eventService.deleteEventById(id);
    }
}
