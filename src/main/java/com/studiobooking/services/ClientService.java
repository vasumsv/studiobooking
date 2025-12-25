package com.studiobooking.services;

import com.studiobooking.dto.ClientRequest;
import com.studiobooking.entities.Client;
import com.studiobooking.repositories.ClientRepository;
import com.studiobooking.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final CurrentUser currentUser;

    public Client create(ClientRequest request) {
        UUID studioId = currentUser.getCurrentStudioId();

        Client client = Client.builder()
                .name(request.getName())
                .phone(request.getPhone())
                .email(request.getEmail())
                .notes(request.getNotes())
                .studioId(studioId)
                .build();

        return clientRepository.save(client);
    }

    public List<Client> getAll() {
        UUID studioId = currentUser.getCurrentStudioId();
        return clientRepository.findByStudioId(studioId);
    }

    public Client getById(UUID id) {
        Client client = clientRepository.findById(id).orElse(null);
        if (client == null) return null;

        UUID studioId = currentUser.getCurrentStudioId();
        if (!client.getStudioId().equals(studioId) && !currentUser.isAdmin()) {
            throw new RuntimeException("Access denied to this client");
        }

        return client;
    }

    public List<Client> searchByName(String name) {
        UUID studioId = currentUser.getCurrentStudioId();
        return clientRepository.findByStudioIdAndNameContainingIgnoreCase(studioId, name);
    }

    public Client update(UUID id, ClientRequest request) {
        Client existing = getById(id); // already checks studio
        if (existing == null) {
            throw new RuntimeException("Client not found");
        }

        if (request.getName() != null) existing.setName(request.getName());
        if (request.getPhone() != null) existing.setPhone(request.getPhone());
        if (request.getEmail() != null) existing.setEmail(request.getEmail());
        if (request.getNotes() != null) existing.setNotes(request.getNotes());

        return clientRepository.save(existing);
    }
}
