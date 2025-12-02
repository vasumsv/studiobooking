package com.studiobooking.services;

import com.studiobooking.dto.BookingRequest;
import com.studiobooking.entities.Booking;
import com.studiobooking.repositories.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingService {

	 private final BookingRepository bookingRepository;

	    public Booking create(BookingRequest req) {
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
	                .build();

	        return bookingRepository.save(booking);
	    }

	    public List<Booking> getRange(LocalDateTime start, LocalDateTime end) {
	        return bookingRepository.findByStartBetween(start, end);
	    }

	    public Booking getById(UUID id) {
	        return bookingRepository.findById(id).orElse(null);
	    }
}
