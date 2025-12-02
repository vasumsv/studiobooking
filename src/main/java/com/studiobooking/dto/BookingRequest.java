package com.studiobooking.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class BookingRequest {

    private String clientName;
    private String clientPhone;
    private String clientEmail;
    private String serviceType;
    private LocalDateTime start;
    private LocalDateTime end;
    private String location;
    private String assignedPhotographers;
    private Double price;
    private String status;
}
