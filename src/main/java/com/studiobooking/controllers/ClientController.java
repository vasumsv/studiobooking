package com.studiobooking.controllers;

import com.studiobooking.dto.ClientRequest;
import com.studiobooking.entities.Client;
import com.studiobooking.services.ClientService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<Client> create(@RequestBody ClientRequest request) {
        return ResponseEntity.ok(clientService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAll() {
        return ResponseEntity.ok(clientService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getById(@PathVariable UUID id) {
        Client client = clientService.getById(id);
        if (client == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(client);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Client>> search(@RequestParam String name) {
        return ResponseEntity.ok(clientService.searchByName(name));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> update(
            @PathVariable UUID id,
            @RequestBody ClientRequest request
    ) {
        return ResponseEntity.ok(clientService.update(id, request));
    }
}
