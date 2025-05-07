package com.raphmei.eventplanner.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReservationRequest {

    @NotNull
    private Long userId;
    @NotNull
    private Long eventId;
    @NotNull
    private int nbSeats;
}
