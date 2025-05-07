package com.raphmei.eventplanner.controller;

import com.raphmei.eventplanner.dto.ReservationDTO;
import com.raphmei.eventplanner.dto.ReservationRequest;
import com.raphmei.eventplanner.entity.Reservation;
import com.raphmei.eventplanner.service.ReservationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/reservations")
@AllArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ReservationDTO reservation(@RequestBody @Valid ReservationRequest request) {
        return reservationService.createReservation(
                request.getUserId(),
                request.getEventId(),
                request.getNbSeats()
        );
    }
    @GetMapping
    public List<ReservationDTO> getAllReservations() {
        return reservationService.findAllReservation();
    }

    @GetMapping("/{id}")
    public ReservationDTO getReservationById(@PathVariable Long id) {
        return reservationService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteReservationById(@PathVariable Long id) {
        reservationService.deleteById(id);
    }
}
