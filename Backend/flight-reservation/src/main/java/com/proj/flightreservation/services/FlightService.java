// src/main/java/com/sample/example/service/UserService.java
package com.proj.flightreservation.services;

import com.proj.flightreservation.models.Flight;
import com.proj.flightreservation.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {

    private final FlightRepository flightRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Flight> getAllFlights() {
        List<Flight> flights = flightRepository.findAll();
        return flights;
    }

    public List<Object[]> getAllFlightInfo() {
        return flightRepository.findAllFlightInfo();
    }

    public Flight createFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    public void deleteFlightById(int flightId) {
        flightRepository.deleteById(flightId);
    }

    public List<Object[]> getFlightsByDestination(String destination) {
        return flightRepository.findFlightsByDestination(destination);
    }
}
