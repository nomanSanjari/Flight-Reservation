package com.proj.flightreservation.services;

import com.proj.flightreservation.models.Crew;
import com.proj.flightreservation.repositories.CrewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CrewService {

    private final CrewRepository crewRepository;

    @Autowired
    public CrewService(CrewRepository crewRepository) {
        this.crewRepository = crewRepository;
    }

    public List<Crew> getAllCrews() {
        return crewRepository.findAll();
    }

    public Optional<Crew> getCrewById(int crewID) {
        return crewRepository.findById(crewID);
    }

    public Crew createCrew(Crew crew) {
        return crewRepository.save(crew);
    }

    public void deleteCrew(int crewID) {
        crewRepository.deleteById(crewID);
    }
}
