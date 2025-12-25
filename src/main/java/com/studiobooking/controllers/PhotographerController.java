package com.studiobooking.controllers;

import com.studiobooking.dto.PhotographerRequest;
import com.studiobooking.entities.Photographer;
import com.studiobooking.services.PhotographerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/photographers")
@RequiredArgsConstructor
public class PhotographerController {

    private final PhotographerService photographerService;

    @PostMapping
    public ResponseEntity<Photographer> create(@RequestBody PhotographerRequest request) {
        return ResponseEntity.ok(photographerService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<Photographer>> getAllActive() {
        return ResponseEntity.ok(photographerService.getAllActive());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Photographer> getById(@PathVariable UUID id) {
        Photographer photographer = photographerService.getById(id);
        if (photographer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(photographer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Photographer> update(
            @PathVariable UUID id,
            @RequestBody PhotographerRequest request
    ) {
        return ResponseEntity.ok(photographerService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> softDelete(@PathVariable UUID id) {
        photographerService.softDelete(id);
        return ResponseEntity.noContent().build();
    }
}
