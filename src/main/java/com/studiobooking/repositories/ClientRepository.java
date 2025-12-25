package com.studiobooking.repositories;

import com.studiobooking.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {

    List<Client> findByStudioId(UUID studioId);

    List<Client> findByStudioIdAndNameContainingIgnoreCase(UUID studioId, String name);
}
