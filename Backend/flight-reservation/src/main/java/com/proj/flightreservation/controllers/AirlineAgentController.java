package com.proj.flightreservation.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.proj.flightreservation.models.AirlineAgent;
import com.proj.flightreservation.services.AirlineAgentService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/airlineAgents")
public class AirlineAgentController {

    private final AirlineAgentService airlineAgentService;

    @Autowired
    public AirlineAgentController(AirlineAgentService airlineAgentService) {
        this.airlineAgentService = airlineAgentService;
    }

    @GetMapping("/getAllAirlineAgents")
    public String getAllAirlineAgents() {
        List<AirlineAgent> airlineAgents = airlineAgentService.getAllAirlineAgents();
        return airlineAgents.toString();
    }

    @GetMapping("/{id}")
    public String getAirlineAgentById(@PathVariable int id) {
        return airlineAgentService.getAirlineAgentById(id).toString();
    }

    @GetMapping
    public String getAirlineAgentByIdBody(@RequestBody @NotNull JsonNode jsonNode) {
        int id = jsonNode.get("id").asInt();

        return airlineAgentService.getAirlineAgentById(id).toString();
    }

}


