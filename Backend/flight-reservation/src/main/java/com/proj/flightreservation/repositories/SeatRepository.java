package com.proj.flightreservation.repositories;

import com.proj.flightreservation.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.math.BigDecimal;

public interface SeatRepository extends JpaRepository<Seat, String> {
    List<Seat> findByFlightID(int flightID);

    List<Seat> findBySeatTypeAndFlightID(String seatType, int flightID);

    List<Seat> findByPriceAndFlightID(BigDecimal price, int flightID);

}
