package com.proj.flightreservation.repositories;

import com.proj.flightreservation.models.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AircraftRepository extends JpaRepository<Aircraft, Integer> {
    // Custom database operations if needed
}