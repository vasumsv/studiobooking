package com.studiobooking.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
@Builder
public class CalendarAvailabilityRequest {

    private UUID photographerId;
    private LocalDate date;
    private LocalTime availableFrom;
    private LocalTime availableTo;
    private String status;
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
