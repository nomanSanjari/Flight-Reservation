package com.proj.flightreservation.services;

import com.proj.flightreservation.models.FlightAttendant;
import com.proj.flightreservation.repositories.FlightAttendantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightAttendantService {

    private final FlightAttendantRepository flightAttendantRepository;

    @Autowired
    public FlightAttendantService(FlightAttendantRepository flightAttendantRepository) {
        this.flightAttendantRepository = flightAttendantRepository;
    }

    public List<FlightAttendant> getAllFlightAttendants() {
        return flightAttendantRepository.findAll();
    }

    public Optional<FlightAttendant> getFlightAttendantById(int id) {
        return flightAttendantRepository.findById(id);
    }

    public FlightAttendant createFlightAttendant(FlightAttendant flightAttendant) {
        return flightAttendantRepository.save(flightAttendant);
    }

    public void deleteFlightAttendant(int id) {
        flightAttendantRepository.deleteById(id);
    }
}
