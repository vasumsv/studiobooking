package com.studiobooking.repositories;

import com.studiobooking.entities.Photographer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PhotographerRepository extends JpaRepository<Photographer, UUID> {

    List<Photographer> findByStudioId(UUID studioId);

    List<Photographer> findByStudioIdAndActiveTrue(UUID studioId);
}
