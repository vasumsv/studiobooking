package com.studiobooking.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtUtil {

    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // ==========================
    // 🔥 GENERATE TOKEN
    // ==========================
    public String generateToken(UUID studioId, String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("studioId", studioId.toString())
                .claim("role", role)
                .setIssuer("StudioBookingApp")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 24 hours
                .signWith(key)
                .compact();
    }

    // ==========================
    // 🔥 EXTRACT EMAIL
    // ==========================
    public String extractEmail(String token) {
        return getAllClaims(token).getSubject();
    }

    // ==========================
    // 🔥 EXTRACT STUDIO ID
    // ==========================
    public UUID extractStudioId(String token) {
        String idString = getAllClaims(token).get("studioId", String.class);
        return UUID.fromString(idString);
    }

    // ==========================
    // 🔥 EXTRACT ROLE
    // ==========================
    public String extractRole(String token) {
        return getAllClaims(token).get("role", String.class);
    }

    // ==========================
    // ✔ COMMON METHOD
    // ==========================
    private Claims getAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
