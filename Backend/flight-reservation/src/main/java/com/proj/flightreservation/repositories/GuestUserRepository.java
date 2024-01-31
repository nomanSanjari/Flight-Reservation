package com.proj.flightreservation.repositories;

import com.proj.flightreservation.models.GuestUser;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GuestUserRepository extends JpaRepository<GuestUser, Integer> {
    // You can add custom queries here if needed
}
