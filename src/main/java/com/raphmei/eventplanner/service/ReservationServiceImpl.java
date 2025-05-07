package com.raphmei.eventplanner.service;

import com.raphmei.eventplanner.dto.ReservationDTO;
import com.raphmei.eventplanner.entity.Event;
import com.raphmei.eventplanner.entity.Reservation;
import com.raphmei.eventplanner.entity.User;
import com.raphmei.eventplanner.repository.EventRepository;
import com.raphmei.eventplanner.repository.ReservationRepository;
import com.raphmei.eventplanner.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    @Override
    public ReservationDTO createReservation(Long userId, Long eventId, int nbSeats) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User doesn't exist"));
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event doesn't exist"));

        if(nbSeats > event.getMaxSeats()){
            throw new RuntimeException("Number of seats exceeded");
        }

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setEvent(event);
        reservation.setNbSeats(nbSeats);
        reservationRepository.save(reservation);

        return mapToDTO(reservation);

    }

    @Override
    public List<ReservationDTO> findAllReservation() {
        return reservationRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public ReservationDTO findById(Long id) {
        Reservation saveReservation = reservationRepository.findById(id).orElseThrow(()-> new RuntimeException("Reservation " + id + " not found"));
        return mapToDTO(saveReservation);
    }

    @Override
    public void deleteById(Long id) {
        reservationRepository.findById(id).orElseThrow(()-> new RuntimeException("Reservation " + id + " not found"));
        reservationRepository.deleteById(id);
    }

    private ReservationDTO mapToDTO(Reservation reservation) {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setEventTitle(reservation.getEvent().getTitle());
        reservationDTO.setUserId(reservation.getUser().getId());
        reservationDTO.setNbSeats(reservation.getNbSeats());
        reservationDTO.setStatus(reservation.getStatus());
        return reservationDTO;
    }
}
