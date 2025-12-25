package com.studiobooking.dto;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
public class CalendarAvailabilityRequest {

    private UUID photographerId;
    private LocalDate date;
    private LocalTime availableFrom;
    private LocalTime availableTo;
    private String status;
}
