package com.studiobooking.dto;

import com.studiobooking.entities.Booking;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class CalendarDayResponse {

    private LocalDate date;
    private List<Booking> bookings;

    // Status for UI color coding:
    private String status; // GREEN / YELLOW / RED / EMPTY
}
