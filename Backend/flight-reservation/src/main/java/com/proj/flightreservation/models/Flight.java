// src/main/java/com/sample/example/model/Flight.java
package com.proj.flightreservation.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Flight")
public class Flight {
    @Id
    @Column(name = "FlightID", nullable = false)
    private int flightID;
    @Column(name = "Origin", nullable = false)
    private String origin;
    @Column(name = "Destination", nullable = false)
    private String destination;
    @Column(name = "DepartureDate", nullable = false)
    private java.sql.Date departureDate;
    @Column(name = "FlightNumber", nullable = false)
    private String flightNumber;
    @Column(name = "AircraftID")
    private int aircraftID;

    public int getFlightID() {
        return flightID;
    }

    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public java.sql.Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(java.sql.Date departureDate) {
        this.departureDate = departureDate;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public int getAircraftID() {
        return aircraftID;
    }

    public void setAircraftID(int aircraftID) {
        this.aircraftID = aircraftID;
    }

    // Constructors (if needed)

    public Flight() {
    }

    public Flight(int flightID, String origin, String destination, java.sql.Date departureDate, String flightNumber,
            int aircraftID) {
        this.flightID = flightID;
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.flightNumber = flightNumber;
        this.aircraftID = aircraftID;
    }
}
