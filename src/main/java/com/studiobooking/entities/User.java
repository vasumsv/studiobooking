package com.studiobooking.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    private String studioName;

    private String role; // STUDIO_OWNER, ADMIN

    @Column(nullable = false)
    private UUID studioId;
}
