package com.raphmei.eventplanner.dto;


import com.raphmei.eventplanner.entity.ReservationStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ReservationDTO {

    private Long userId;
    @Min(1)
    private int nbSeats;
    private ReservationStatus status;
    @NotBlank
    private String eventTitle;

}
