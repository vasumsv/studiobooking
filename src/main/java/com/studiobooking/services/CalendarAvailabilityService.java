package com.studiobooking.services;

import com.studiobooking.dto.CalendarAvailabilityRequest;
import com.studiobooking.entities.CalendarAvailability;
import com.studiobooking.repositories.CalendarAvailabilityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CalendarAvailabilityService {

    private final CalendarAvailabilityRepository repository;

    public CalendarAvailability setAvailability(CalendarAvailabilityRequest req) {
        CalendarAvailability availability = CalendarAvailability.builder()
                .photographerId(req.getPhotographerId())
                .date(req.getDate())
                .availableFrom(req.getAvailableFrom())
                .availableTo(req.getAvailableTo())
                .status(req.getStatus())
                .build();

        return repository.save(availability);
    }

    public List<CalendarAvailability> getAvailabilityForPhotographer(UUID id) {
        return repository.findByPhotographerId(id);
    }

    public List<CalendarAvailability> getAvailabilityForDate(LocalDate date) {
        return repository.findByDate(date);
    }
}
