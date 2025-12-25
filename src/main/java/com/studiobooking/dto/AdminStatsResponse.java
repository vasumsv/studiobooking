package com.studiobooking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdminStatsResponse {

    private long totalStudios;
    private long totalUsers;
    private long totalBookings;
    private double totalRevenue; // from completed/confirmed bookings
}
