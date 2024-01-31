package com.proj.flightreservation.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.proj.flightreservation.models.Aircraft;
import com.proj.flightreservation.services.AircraftService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/aircrafts")
public class AircraftController {

    private final AircraftService aircraftService;

    @Autowired
    public AircraftController(AircraftService aircraftService) {
        this.aircraftService = aircraftService;
    }

    @GetMapping("/getAllAircrafts")
    public List<Aircraft> getAllAircrafts() {
        return aircraftService.getAllAircrafts();
    }

    @GetMapping("/{id}")
    public Aircraft getAircraftById(@PathVariable int id) {
        Optional<Aircraft> aircraft = aircraftService.getAircraftById(id);
        return aircraft.orElse(null);
    }

    @PostMapping("/createAircraft")
    public Aircraft createAircraft(@RequestBody @NotNull JsonNode jsonNode) {
        String aircraftModel = jsonNode.get("aircraftModel").asText();
        int numEconomySeats = jsonNode.get("numEconomySeats").asInt();
        int numComfortSeats = jsonNode.get("numComfortSeats").asInt();
        int numBusinessSeats = jsonNode.get("numBusinessSeats").asInt();
        boolean inUse = jsonNode.get("inUse").asBoolean();

        Aircraft aircraft = new Aircraft(aircraftModel, numEconomySeats, numComfortSeats, numBusinessSeats, inUse);
        return aircraftService.createAircraft(aircraft);
    }

    @DeleteMapping("/{id}")
    public void deleteAircraft(@PathVariable int id) {
        aircraftService.deleteAircraft(id);
    }

    // Additional endpoint methods can be added here as needed
}
