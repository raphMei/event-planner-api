package com.raphmei.eventplanner.repository;

import com.raphmei.eventplanner.entity.Event;
import com.raphmei.eventplanner.entity.Reservation;
import com.raphmei.eventplanner.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findReservationByEvent(Event event);
    List<Reservation> findReservationByUser(User user);

}
