package com.studiobooking.repositories;

import com.studiobooking.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {

    List<Booking> findByStartBetween(LocalDateTime start, LocalDateTime end);

    List<Booking> findByAssignedPhotographersContaining(String photographerId);
}
