// src/main/java/com/sample/example/repository/UserRepository.java
package com.proj.flightreservation.repositories;

import com.proj.flightreservation.models.FlightAttendant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FlightAttendantRepository extends JpaRepository<FlightAttendant, Integer> {
    // You can add custom queries here if needed
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM FlightAttendant u WHERE u.UserID = :UserID AND u.Pwd_FlightAttendant = :Pwd_FlightAttendant")
    boolean checkPasswordForUser(@Param("UserID") int userId, @Param("Pwd_FlightAttendant") String password);
}
