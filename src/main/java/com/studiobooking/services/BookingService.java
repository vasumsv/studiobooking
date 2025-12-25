package com.studiobooking.services;

import com.studiobooking.dto.BookingConflictResponse;
import com.studiobooking.dto.BookingRequest;
import com.studiobooking.entities.Booking;
import com.studiobooking.repositories.BookingRepository;
import com.studiobooking.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final CurrentUser currentUser;

    public Booking create(BookingRequest req) {

        UUID studioId = currentUser.getCurrentStudioId();

        Booking booking = Booking.builder()
                .clientName(req.getClientName())
                .clientPhone(req.getClientPhone())
                .clientEmail(req.getClientEmail())
                .serviceType(req.getServiceType())
                .start(req.getStart())
                .end(req.getEnd())
                .location(req.getLocation())
                .assignedPhotographers(req.getAssignedPhotographers())
                .price(req.getPrice())
                .status(req.getStatus())
                .studioId(studioId)
                .build();

        return bookingRepository.save(booking);
    }

    public List<Booking> getRange(LocalDateTime start, LocalDateTime end) {
        UUID studioId = currentUser.getCurrentStudioId();
        return bookingRepository.findByStudioIdAndStartBetween(studioId, start, end);
    }

    public Booking getById(UUID id) {
        Booking booking = bookingRepository.findById(id).orElse(null);
        if (booking == null) return null;

        // Simple safeguard: only allow same studio OR admin
        UUID studioId = currentUser.getCurrentStudioId();
        if (!booking.getStudioId().equals(studioId) && !currentUser.isAdmin()) {
            throw new RuntimeException("Access denied to this booking");
        }
        return booking;
    }

    public BookingConflictResponse checkForConflict(BookingRequest req) {

        UUID studioId = currentUser.getCurrentStudioId();

        List<Booking> overlapping = bookingRepository.findOverlapping(
                studioId,
                req.getStart(),
                req.getEnd()
        );

        if (!overlapping.isEmpty()) {
            return new BookingConflictResponse(
                    true,
                    "Time slot overlaps with another booking."
            );
        }

        // (Simplified photographer conflict check for now)
        return new BookingConflictResponse(false, "No conflicts.");
    }
}
