package com.raphmei.eventplanner.configuration;

import com.raphmei.eventplanner.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class JwtUtils {

    @Value("${app.secret.key}")
    private String secretKey;

    @Value("${app.expiration-time}")
    private Long expirationTime;

    public String generateToken(User user) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + expirationTime);
        return Jwts.builder()
                .setSubject(user.getId().toString())
                .claim("username",user.getUsername())
                .claim("email", user.getEmail())
                .claim("role",user.getRole())
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()), SignatureAlgorithm.HS512)
                .compact();

    }

    public String extractEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("email", String.class);
    }
}
