package com.raphmei.eventplanner.service;

import com.raphmei.eventplanner.dto.EventDTO;
import com.raphmei.eventplanner.entity.Event;
import com.raphmei.eventplanner.repository.EventRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Override
    public EventDTO createEvent(Event event) {
      if(eventRepository.existsEventByTitle(event.getTitle())) {
          throw new RuntimeException("Event already exists");
      }
      Event savedEvent = eventRepository.save(event);
      return mapToDTO(savedEvent);
    }

    @Override
    public List<EventDTO> getAllEvents() {
        return eventRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EventDTO getEventById(Long id) {
        Event event = eventRepository.findById(id).orElseThrow(() -> new RuntimeException("Event not found"));
        return  mapToDTO(event);
    }

    @Override
    public void deleteEventById(Long id) {
       Event event = eventRepository.findById(id).orElseThrow(() -> new RuntimeException("Event not found"));
       eventRepository.delete(event);
    }

    private EventDTO mapToDTO(Event event) {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(event.getId());
        eventDTO.setTitle(event.getTitle());
        eventDTO.setDescription(event.getDescription());
        eventDTO.setLocation(event.getLocation());
        eventDTO.setMaxSeats(event.getMaxSeats());
        eventDTO.setDate(event.getDate());
        return eventDTO;
    }
}
