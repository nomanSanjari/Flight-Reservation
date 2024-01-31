package com.proj.flightreservation.services;

import com.proj.flightreservation.models.AirlineAgent;
import com.proj.flightreservation.models.FlightAttendant;
import com.proj.flightreservation.repositories.AirlineAgentRepository;
import com.proj.flightreservation.repositories.FlightAttendantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirlineAgentService {

    private final AirlineAgentRepository airlineAgentRepository;

    @Autowired
    public AirlineAgentService(AirlineAgentRepository airlineAgentRepository) {
        this.airlineAgentRepository = airlineAgentRepository;
    }

    public List<AirlineAgent> getAllAirlineAgents() {
        return airlineAgentRepository.findAll();
    }

    public Optional<AirlineAgent> getAirlineAgentById(int id) {
        return airlineAgentRepository.findById(id);
    }

    public AirlineAgent createAirlineAgent(AirlineAgent airlineAgent) {
        return airlineAgentRepository.save(airlineAgent);
    }

    public void deleteAirlineAgent(int id) {
        airlineAgentRepository.deleteById(id);
    }
}
