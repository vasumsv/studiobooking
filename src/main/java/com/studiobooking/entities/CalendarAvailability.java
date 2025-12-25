package com.studiobooking.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "calendar_availability")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CalendarAvailability {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID studioId;

    @Column(nullable = false)
    private UUID photographerId;

    @Column(nullable = false)
    private LocalDate date;

    private LocalTime availableFrom;

    private LocalTime availableTo;

    private String status; // WORKING, HOLIDAY, LEAVE
}
