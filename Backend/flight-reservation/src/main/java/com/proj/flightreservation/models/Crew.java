package com.proj.flightreservation.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Crew")
public class Crew {

    @Id
    @Column(name = "CrewID", nullable = false)
    private int crewID;

    @Column(name = "FlightID", nullable = false)
    private int flightID;

    @Column(name = "CrewName", nullable = false, length = 50)
    private String crewName;

    // Constructors
    public Crew() {
    }

    public Crew(int crewID, int flightID, String crewName) {
        this.crewID = crewID;
        this.flightID = flightID;
        this.crewName = crewName;
    }

    // Getters and Setters
    public int getCrewID() {
        return crewID;
    }

    public void setCrewID(int crewID) {
        this.crewID = crewID;
    }

    public int getFlightID() {
        return flightID;
    }

    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }

    public String getCrewName() {
        return crewName;
    }

    public void setCrewName(String crewName) {
        this.crewName = crewName;
    }

    @Override
    public String toString() {
        return "Crew" +
                "CrewID=" + crewID +
                ", FlightID='" + flightID + '\'' +
                ", crewName=" + crewName +
                '}';
    }
}
