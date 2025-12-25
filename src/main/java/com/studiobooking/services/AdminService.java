package com.studiobooking.services;

import com.studiobooking.dto.AdminStatsResponse;
import com.studiobooking.entities.User;
import com.studiobooking.repositories.BookingRepository;
import com.studiobooking.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;

    public AdminStatsResponse getStats() {

        long totalUsers = userRepository.count();
        long totalStudios = userRepository.findAll().stream()
                .map(User::getStudioName)
                .filter(s -> s != null && !s.isBlank())
                .distinct()
                .count();

        long totalBookings = bookingRepository.count();
        Double totalRevenue = bookingRepository.getTotalRevenue();
        if (totalRevenue == null) totalRevenue = 0.0;

        return new AdminStatsResponse(
                totalStudios,
                totalUsers,
                totalBookings,
                totalRevenue
        );
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
