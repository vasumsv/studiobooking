package com.studiobooking.services;

import com.studiobooking.dto.*;
import com.studiobooking.entities.User;
import com.studiobooking.repositories.UserRepository;
import com.studiobooking.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;

    // ========================
    // SIGNUP
    // ========================
    public AuthResponse signup(SignupRequest request) {

        UUID studioId = UUID.randomUUID(); // each signup = new studio

        User user = User.builder()
                .email(request.getEmail())
                .password(new BCryptPasswordEncoder().encode(request.getPassword()))
                .studioName(request.getStudioName())
                .role("STUDIO_OWNER")
                .studioId(studioId)
                .build();

        userRepository.save(user);

        // 🔥 NEW TOKEN FORMAT
        String token = jwtUtil.generateToken(
                user.getStudioId(),
                user.getEmail(),
                user.getRole()
        );

        return new AuthResponse(token);
    }

    // ========================
    // LOGIN
    // ========================
    public AuthResponse login(AuthRequest request) {

        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword())
        );

        // Get user (required to fetch studioId + role)
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 🔥 NEW TOKEN FORMAT
        String token = jwtUtil.generateToken(
                user.getStudioId(),
                user.getEmail(),
                user.getRole()
        );

        return new AuthResponse(token);
    }
}
