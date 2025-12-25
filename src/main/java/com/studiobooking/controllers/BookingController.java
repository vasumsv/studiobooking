package com.studiobooking.controllers;

import com.studiobooking.dto.BookingConflictResponse;
import com.studiobooking.dto.BookingRequest;
import com.studiobooking.entities.Booking;
import com.studiobooking.services.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody BookingRequest request) {
        return ResponseEntity.ok(bookingService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable UUID id) {
        return ResponseEntity.ok(bookingService.getById(id));
    }

    @GetMapping("/range")
    public ResponseEntity<List<Booking>> getBookingsInRange(
            @RequestParam LocalDateTime start,
            @RequestParam LocalDateTime end
    ) {
        return ResponseEntity.ok(bookingService.getRange(start, end));
    }

    @PostMapping("/check-conflict")
    public ResponseEntity<BookingConflictResponse> checkConflict(
            @RequestBody BookingRequest request
    ) {
        return ResponseEntity.ok(bookingService.checkForConflict(request));
    }

}


