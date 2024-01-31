// src/main/java/com/sample/example/repository/FlightRepository.java
package com.proj.flightreservation.repositories;

import com.proj.flightreservation.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param; // Add this import statement

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Integer> {
    // Existing methods

    @Query("SELECT f.origin, f.destination, f.departureDate, f.flightNumber FROM Flight f")
    List<Object[]> findAllFlightInfo();

    @Query("SELECT f.origin, f.departureDate, f.flightNumber FROM Flight f WHERE f.destination = :destination")
    List<Object[]> findFlightsByDestination(@Param("destination") String destination);
}
