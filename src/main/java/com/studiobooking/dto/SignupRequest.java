package com.studiobooking.dto;

import lombok.Data;

@Data
public class SignupRequest {
    private String studioName;
    private String email;
    private String password;
}
