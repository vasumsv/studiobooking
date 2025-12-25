package com.studiobooking.repositories;

import com.studiobooking.entities.CalendarAvailability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface CalendarAvailabilityRepository extends JpaRepository<CalendarAvailability, UUID> {

    List<CalendarAvailability> findByPhotographerIdAndStudioId(UUID photographerId, UUID studioId);

    List<CalendarAvailability> findByDateAndStudioId(LocalDate date, UUID studioId);
}
