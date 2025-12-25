package com.studiobooking.security;

import com.studiobooking.entities.User;
import com.studiobooking.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CurrentUser {

    private final UserRepository userRepository;

    public String getCurrentEmail() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getName() == null) {
            throw new RuntimeException("No authenticated user");
        }
        return auth.getName();
    }

    public UUID getCurrentStudioId() {
        String email = getCurrentEmail();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found for email: " + email));
        return user.getStudioId();
    }

    public boolean isAdmin() {
        String email = getCurrentEmail();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found for email: " + email));
        return "ADMIN".equalsIgnoreCase(user.getRole());
    }
}
