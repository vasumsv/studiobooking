package com.studiobooking.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "photographers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Photographer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true)
    private String email;

    private String phone;

    private String specialization; // e.g. "Wedding, Pre-wedding, Portrait"

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private UUID studioId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.active = true;
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
