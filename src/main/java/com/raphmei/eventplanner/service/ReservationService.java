package com.raphmei.eventplanner.service;



import com.raphmei.eventplanner.dto.ReservationDTO;

import java.util.List;

public interface ReservationService {
    ReservationDTO createReservation(Long userId, Long eventId, int nbSeats);
    List<ReservationDTO> findAllReservation();
   ReservationDTO findById(Long id);
    void deleteById(Long id);
}
