package com.proj.flightreservation.services;

import com.proj.flightreservation.models.Seat;
import com.proj.flightreservation.repositories.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

import java.util.List;

@Service
public class SeatService {

    private final SeatRepository seatRepository;

    @Autowired
    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public List<Seat> getSeatsByFlightID(int flightID) {
        return seatRepository.findByFlightID(flightID);
    }

    public List<Seat> getSeatsByTypeAndFlightID(String seatType, int flightID) {
        return seatRepository.findBySeatTypeAndFlightID(seatType, flightID);
    }

    public List<Seat> getSeatsByPriceAndFlightID(BigDecimal price, int flightID) {
        return seatRepository.findByPriceAndFlightID(price, flightID);
    }
}
