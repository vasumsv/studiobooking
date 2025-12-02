package com.studiobooking.controllers;

import com.studiobooking.dto.CalendarAvailabilityRequest;
import com.studiobooking.entities.CalendarAvailability;
import com.studiobooking.services.CalendarAvailabilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/calendar")
@RequiredArgsConstructor
public class CalendarAvailabilityController {

    private final CalendarAvailabilityService service;

    @PostMapping("/availability")
    public ResponseEntity<CalendarAvailability> setAvailability(
            @RequestBody CalendarAvailabilityRequest req) {
        return ResponseEntity.ok(service.setAvailability(req));
    }

    @GetMapping("/photographer/{id}")
    public ResponseEntity<List<CalendarAvailability>> getForPhotographer(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getAvailabilityForPhotographer(id));
    }

    @GetMapping("/date")
    public ResponseEntity<List<CalendarAvailability>> getForDate(
            @RequestParam LocalDate date) {

        return ResponseEntity.ok(service.getAvailabilityForDate(date));
    }
}
