package com.studiobooking.repositories;

import com.studiobooking.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {

    // Old global method (keep if needed)
    List<Booking> findByStartBetween(LocalDateTime start, LocalDateTime end);

    // Multi-tenant: all bookings for studio
    List<Booking> findByStudioId(UUID studioId);

    // Multi-tenant: bookings for studio in range
    List<Booking> findByStudioIdAndStartBetween(UUID studioId, LocalDateTime start, LocalDateTime end);

    // Overlap check per studio
    @Query("""
        SELECT b FROM Booking b
        WHERE b.studioId = :studioId
          AND b.start < :end
          AND b.end > :start
    """)
    List<Booking> findOverlapping(UUID studioId, LocalDateTime start, LocalDateTime end);

    // Revenue for a single studio
    @Query("""
        SELECT COALESCE(SUM(b.price), 0)
        FROM Booking b
        WHERE b.studioId = :studioId
          AND UPPER(b.status) IN ('CONFIRMED', 'COMPLETED')
    """)
    Double getTotalRevenueByStudio(UUID studioId);

    // Global revenue (admin)
    @Query("""
        SELECT COALESCE(SUM(b.price), 0)
        FROM Booking b
        WHERE UPPER(b.status) IN ('CONFIRMED', 'COMPLETED')
    """)
    Double getTotalRevenue();
}
