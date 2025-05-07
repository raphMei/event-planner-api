package com.raphmei.eventplanner.filter;

import com.raphmei.eventplanner.configuration.JwtUtils;
import com.raphmei.eventplanner.entity.Role;
import com.raphmei.eventplanner.entity.User;
import com.raphmei.eventplanner.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;

    private final UserRepository userRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            String email = jwtUtils.extractEmail(token);
            Optional<User> user = userRepository.findUserByEmail(email);
            if (user.isPresent()) {
                User userEntity = user.get();
                String authority = "ROLE_" + userEntity.getRole().name();
                SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority);
                List<GrantedAuthority> authorities = List.of(grantedAuthority);
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(userEntity, null,authorities);

                if (SecurityContextHolder.getContext().getAuthentication() == null) {
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }

        }
        filterChain.doFilter(request, response);
    }
}
