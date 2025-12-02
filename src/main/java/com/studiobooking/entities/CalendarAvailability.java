package com.studiobooking.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "calendar_availability")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CalendarAvailability {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID photographerId;

    @Column(nullable = false)
    private LocalDate date;

    private LocalTime availableFrom;

    private LocalTime availableTo;

    // Example: "LEAVE", "WORKING", "HOLIDAY"
    private String status;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getPhotographerId() {
		return photographerId;
	}

	public void setPhotographerId(UUID photographerId) {
		this.photographerId = photographerId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getAvailableFrom() {
		return availableFrom;
	}

	public void setAvailableFrom(LocalTime availableFrom) {
		this.availableFrom = availableFrom;
	}

	public LocalTime getAvailableTo() {
		return availableTo;
	}

	public void setAvailableTo(LocalTime availableTo) {
		this.availableTo = availableTo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    
}
