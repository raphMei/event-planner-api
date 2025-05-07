package com.raphmei.eventplanner.controller;

import com.raphmei.eventplanner.configuration.JwtUtils;
import com.raphmei.eventplanner.dto.LoginRequest;
import com.raphmei.eventplanner.dto.RegisterRequest;
import com.raphmei.eventplanner.entity.User;
import com.raphmei.eventplanner.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @PostMapping("/register")
    public User register(@RequestBody @Valid RegisterRequest request) {
        if(userRepository.findUserByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already in use");
        }else {
            User user = new User();
            user.setEmail(request.getEmail());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setUsername(request.getUsername());
            user.setRole(request.getRole());
            userRepository.save(user);
            return user;
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest request) {
        User user = userRepository.findUserByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("Email not found"));

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid password");
        }
        String token = jwtUtils.generateToken(user);
        return new ResponseEntity<>(token, HttpStatus.OK);

    }

}
