package com.proj.flightreservation.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.proj.flightreservation.models.FlightAttendant;
import com.proj.flightreservation.models.RegisteredUser;
import com.proj.flightreservation.services.FlightAttendantService;
import com.proj.flightreservation.services.RegisteredUserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/flightAttendants")
public class FlightAttendantController {

    private final FlightAttendantService flightAttendantService;

    @Autowired
    public FlightAttendantController(FlightAttendantService flightAttendantService) {
        this.flightAttendantService = flightAttendantService;
    }

    @GetMapping("/getAllFlightAttendants")
    public String getAllFlightAttendants() {
        List<FlightAttendant> flightAttendants = flightAttendantService.getAllFlightAttendants();
        return flightAttendants.toString();
    }

    @GetMapping("/{id}")
    public String getFlightAttendantById(@PathVariable int id) {
        return flightAttendantService.getFlightAttendantById(id).toString();
    }

    @GetMapping
    public String getFlightAttendantByIdBody(@RequestBody @NotNull JsonNode jsonNode) {
        int id = jsonNode.get("id").asInt();

        return flightAttendantService.getFlightAttendantById(id).toString();
    }

}


