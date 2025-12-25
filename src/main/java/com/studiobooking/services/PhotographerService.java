package com.studiobooking.services;

import com.studiobooking.dto.PhotographerRequest;
import com.studiobooking.entities.Photographer;
import com.studiobooking.repositories.PhotographerRepository;
import com.studiobooking.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PhotographerService {

    private final PhotographerRepository photographerRepository;
    private final CurrentUser currentUser;

    public Photographer create(PhotographerRequest req) {
        UUID studioId = currentUser.getCurrentStudioId();

        Photographer photographer = Photographer.builder()
                .name(req.getName())
                .email(req.getEmail())
                .phone(req.getPhone())
                .specialization(req.getSpecialization())
                .active(true)
                .studioId(studioId)
                .build();

        return photographerRepository.save(photographer);
    }

    public List<Photographer> getAllActive() {
        UUID studioId = currentUser.getCurrentStudioId();
        return photographerRepository.findByStudioIdAndActiveTrue(studioId);
    }

    public Photographer getById(UUID id) {
        Photographer photographer = photographerRepository.findById(id).orElse(null);
        if (photographer == null) return null;

        UUID studioId = currentUser.getCurrentStudioId();
        if (!photographer.getStudioId().equals(studioId) && !currentUser.isAdmin()) {
            throw new RuntimeException("Access denied to this photographer");
        }

        return photographer;
    }

    public Photographer update(UUID id, PhotographerRequest req) {
        Photographer existing = getById(id);
        if (existing == null) {
            throw new RuntimeException("Photographer not found");
        }

        if (req.getName() != null) existing.setName(req.getName());
        if (req.getEmail() != null) existing.setEmail(req.getEmail());
        if (req.getPhone() != null) existing.setPhone(req.getPhone());
        if (req.getSpecialization() != null) existing.setSpecialization(req.getSpecialization());
        if (req.getActive() != null) existing.setActive(req.getActive());

        return photographerRepository.save(existing);
    }

    public void softDelete(UUID id) {
        Photographer existing = getById(id);
        if (existing == null) {
            throw new RuntimeException("Photographer not found");
        }

        existing.setActive(false);
        photographerRepository.save(existing);
    }
}
