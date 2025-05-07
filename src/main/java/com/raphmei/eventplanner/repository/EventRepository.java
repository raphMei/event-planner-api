package com.raphmei.eventplanner.repository;

import com.raphmei.eventplanner.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
    boolean existsEventByTitle(String title);
}
