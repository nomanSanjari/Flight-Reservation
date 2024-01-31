// src/main/java/com/sample/example/controller/FlightController.java
package com.proj.flightreservation.controllers;

import java.util.List; // Add this import statement

import com.fasterxml.jackson.databind.JsonNode;
import com.proj.flightreservation.models.Flight;
import com.proj.flightreservation.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.jetbrains.annotations.NotNull;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/flights")
public class FlightController {

    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/getAllFlightInfo")
    public List<Flight> getAllFlightInfo() {
        return flightService.getAllFlights();
    }

    @GetMapping("/showFlightInfo")
    public List<Map<String, Object>> showFlightInfo() {
        List<Object[]> flightsInfo = flightService.getAllFlightInfo();

        return flightsInfo.stream()
                .map(info -> Map.of(
                        "origin", info[0],
                        "destination", info[1],
                        "departureDate", info[2],
                        "flightNumber", info[3]))
                .toList();
    }

    @PostMapping("/createFlight")
    public Flight createFlight(@RequestBody @NotNull JsonNode jsonNode) {
        int flightID = jsonNode.get("flightID").asInt();
        String origin = jsonNode.get("origin").asText();
        String destination = jsonNode.get("destination").asText();
        String departureDate = jsonNode.get("departureDate").asText(); // Assuming date as a string for simplicity
        String flightNumber = jsonNode.get("flightNumber").asText();
        int aircraftID = jsonNode.get("aircraftID").asInt();

        // Convert departureDate string to java.sql.Date (you may need to adjust the
        // parsing logic)
        java.sql.Date parsedDepartureDate = java.sql.Date.valueOf(departureDate);

        Flight flight = new Flight(flightID, origin, destination, parsedDepartureDate, flightNumber, aircraftID);

        return flightService.createFlight(flight);
    }

    @DeleteMapping("/deleteFlight/{flightId}")
    public void deleteFlight(@PathVariable int flightId) {
        flightService.deleteFlightById(flightId);
    }

    @GetMapping("/getFlightsByDestination/{destination}")
    public List<Map<String, Object>> getFlightsByDestination(@PathVariable String destination) {
        List<Object[]> flightsInfo = flightService.getFlightsByDestination(destination);

        return flightsInfo.stream()
                .map(info -> Map.of(
                        "origin", info[0],
                        "departureDate", info[1],
                        "flightNumber", info[2]))
                .toList();
    }
}
