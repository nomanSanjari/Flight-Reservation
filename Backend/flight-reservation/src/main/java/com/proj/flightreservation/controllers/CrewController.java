package com.proj.flightreservation.controllers;

import com.proj.flightreservation.models.Crew;
import com.proj.flightreservation.services.CrewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/crew")
public class CrewController {

    private final CrewService crewService;

    @Autowired
    public CrewController(CrewService crewService) {
        this.crewService = crewService;
    }

    @GetMapping("/getAllCrews")
    public List<Crew> getAllCrews() {
        return crewService.getAllCrews();
    }

    @GetMapping("/{crewID}")
    public Crew getCrewById(@PathVariable int crewID) {
        return crewService.getCrewById(crewID).orElse(null);
    }

    @PostMapping("/createCrew")
    public Crew createCrew(@RequestBody Crew crew) {
        return crewService.createCrew(crew);
    }

    @DeleteMapping("/{crewID}")
    public void deleteCrew(@PathVariable int crewID) {
        crewService.deleteCrew(crewID);
    }
}
