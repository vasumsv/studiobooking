package com.studiobooking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookingConflictResponse {

    private boolean conflict;
    private String message;
}
