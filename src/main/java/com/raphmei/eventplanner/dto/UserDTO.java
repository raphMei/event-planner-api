package com.raphmei.eventplanner.dto;

import com.raphmei.eventplanner.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDTO {

    private Long id;
    @NotBlank
    private String username;
    @Email
    @NotBlank
    private String email;
    private Role role;
    private LocalDateTime createdAt;
}
