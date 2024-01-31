package com.proj.flightreservation.controllers;

import com.proj.flightreservation.models.Seat;
import com.proj.flightreservation.services.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/seats")
public class SeatController {

    private final SeatService seatService;

    @Autowired
    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping("/byFlight/{flightID}")
    public List<Seat> getSeatsByFlightID(@PathVariable int flightID) {
        return seatService.getSeatsByFlightID(flightID);
    }

    @GetMapping("/byTypeAndFlight/{seatType}/{flightID}")
    public List<Seat> getSeatsByTypeAndFlightID(@PathVariable String seatType, @PathVariable int flightID) {
        return seatService.getSeatsByTypeAndFlightID(seatType, flightID);
    }

    @GetMapping("/byPriceAndFlight/{price}/{flightID}")
    public List<Seat> getSeatsByPriceAndFlightID(@PathVariable BigDecimal price, @PathVariable int flightID) {
        return seatService.getSeatsByPriceAndFlightID(price, flightID);
    }
}
