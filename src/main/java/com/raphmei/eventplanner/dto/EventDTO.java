package com.raphmei.eventplanner.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventDTO {
    private Long id;
    @NotBlank
    private String title;
    private String description;
    @NotBlank
    private String location;
    @NotNull
    private LocalDateTime date;
    @NotNull
    private int maxSeats;
}
