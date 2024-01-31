package com.proj.flightreservation.repositories;

import com.proj.flightreservation.models.Crew;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrewRepository extends JpaRepository<Crew, Integer> {
    // Custom database operations if needed
}
