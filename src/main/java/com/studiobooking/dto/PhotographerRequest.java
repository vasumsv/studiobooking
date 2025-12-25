package com.studiobooking.dto;

import lombok.Data;

@Data
public class PhotographerRequest {

    private String name;
    private String email;
    private String phone;
    private String specialization;
    private Boolean active; // optional for updates
}
