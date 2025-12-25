package com.studiobooking.controllers;

import com.studiobooking.dto.CalendarAvailabilityRequest;
import com.studiobooking.entities.CalendarAvailability;
import com.studiobooking.security.JwtUtil;
import com.studiobooking.services.CalendarAvailabilityService;

import jakarta.servlet.http.HttpServletRequest;

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
    private final JwtUtil jwtUtil;

    @PostMapping("/availability")
    public ResponseEntity<CalendarAvailability> setAvailability(
            HttpServletRequest request,
            @RequestBody CalendarAvailabilityRequest req) {

        UUID studioId = jwtUtil.extractStudioId(request.getHeader("Authorization").substring(7));

        return ResponseEntity.ok(service.setAvailability(req, studioId));
    }

    @GetMapping("/photographer/{id}")
    public ResponseEntity<List<CalendarAvailability>> getForPhotographer(
            HttpServletRequest request,
            @PathVariable UUID id) {

        UUID studioId = jwtUtil.extractStudioId(request.getHeader("Authorization").substring(7));

        return ResponseEntity.ok(service.getAvailabilityForPhotographer(id, studioId));
    }

    @GetMapping("/date")
    public ResponseEntity<List<CalendarAvailability>> getForDate(
            HttpServletRequest request,
            @RequestParam LocalDate date) {

        UUID studioId = jwtUtil.extractStudioId(request.getHeader("Authorization").substring(7));

        return ResponseEntity.ok(service.getAvailabilityForDate(date, studioId));
    }
}
