package com.studiobooking.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String clientName;

    @Column(nullable = false)
    private String clientPhone;

    private String clientEmail;

    private String serviceType;

    @Column(nullable = false)
    private LocalDateTime start;

    @Column(nullable = false)
    private LocalDateTime end;

    private String location;

    @Column(nullable = false)
    private String status; // TENTATIVE, CONFIRMED, COMPLETED, CANCELLED

    private Double price;

    @Column(name = "assigned_photographers")
    private String assignedPhotographers; // comma separated UUID list
}
