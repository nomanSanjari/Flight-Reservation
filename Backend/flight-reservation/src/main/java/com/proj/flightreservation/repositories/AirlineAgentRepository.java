package com.proj.flightreservation.repositories;

import com.proj.flightreservation.models.AirlineAgent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AirlineAgentRepository extends JpaRepository<AirlineAgent, Integer> {
    // You can add custom queries here if needed
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM AirlineAgent u WHERE u.UserID = :UserID AND u.Pwd_AirlineAgent = :Pwd_AirlineAgent")
    boolean checkPasswordForUser(@Param("UserID") int userId, @Param("Pwd_AirlineAgent") String password);
}
