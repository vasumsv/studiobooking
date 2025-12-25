package com.studiobooking.dto;

import lombok.Data;

@Data
public class ClientRequest {
    private String name;
    private String phone;
    private String email;
    private String notes;
}
