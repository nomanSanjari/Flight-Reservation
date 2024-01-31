package com.proj.flightreservation.repositories;

import com.proj.flightreservation.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
//    @Query("SELECT u FROM User u LEFT JOIN FETCH u.registeredUser ru WHERE u.Email = :Email")
//    User findByUserEmail(@Param("Email") String email);

//    @Query("SELECT u FROM User u LEFT JOIN FETCH RegisteredUser ru ON u.userID = ru.userID WHERE u.email = :Email")
//    User findByUserEmail(@Param("Email") String email);

    @Query("SELECT u FROM User u WHERE u.Email = :Email")
    User findByUserEmail(@Param("Email") String email);
}
