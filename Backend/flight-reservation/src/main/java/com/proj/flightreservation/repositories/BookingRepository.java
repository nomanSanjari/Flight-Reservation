package com.proj.flightreservation.repositories;

import com.proj.flightreservation.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    // Custom database operations if needed
}
