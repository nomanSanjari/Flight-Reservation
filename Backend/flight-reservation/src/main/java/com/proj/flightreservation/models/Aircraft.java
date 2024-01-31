package com.proj.flightreservation.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Aircraft")
public class Aircraft {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aircraftID", nullable = false)
    private int aircraftID;

    @Column(name = "aircraftModel", nullable = false)
    private String aircraftModel;

    @Column(name = "numEconomySeats", nullable = false)
    private int numEconomySeats;

    @Column(name = "numComfortSeats", nullable = false)
    private int numComfortSeats;

    @Column(name = "numBusinessSeats", nullable = false)
    private int numBusinessSeats;

    @Column(name = "inUse", nullable = false)
    private boolean inUse;

    // Constructors
    public Aircraft() {
    }

    public Aircraft( String aircraftModel, int numEconomySeats, int numComfortSeats, int numBusinessSeats,
            boolean inUse) {
        this.aircraftModel = aircraftModel;
        this.numEconomySeats = numEconomySeats;
        this.numComfortSeats = numComfortSeats;
        this.numBusinessSeats = numBusinessSeats;
        this.inUse = inUse;
    }

    // Getters
    public int getAircraftID() {
        return aircraftID;
    }

    public String getAircraftModel() {
        return aircraftModel;
    }

    public int getNumEconomySeats() {
        return numEconomySeats;
    }

    public int getNumComfortSeats() {
        return numComfortSeats;
    }

    public int getNumBusinessSeats() {
        return numBusinessSeats;
    }

    public boolean isInUse() {
        return inUse;
    }

    // Setters
    public void setAircraftID(int aircraftID) {
        this.aircraftID = aircraftID;
    }

    public void setAircraftModel(String aircraftModel) {
        this.aircraftModel = aircraftModel;
    }

    public void setNumEconomySeats(int numEconomySeats) {
        this.numEconomySeats = numEconomySeats;
    }

    public void setNumComfortSeats(int numComfortSeats) {
        this.numComfortSeats = numComfortSeats;
    }

    public void setNumBusinessSeats(int numBusinessSeats) {
        this.numBusinessSeats = numBusinessSeats;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }

    @Override
    public String toString() {
        return "Aircraft{" +
                "aircraftID=" + aircraftID +
                ", aircraftModel='" + aircraftModel + '\'' +
                ", numEconomySeats=" + numEconomySeats +
                ", numComfortSeats=" + numComfortSeats +
                ", numBusinessSeats=" + numBusinessSeats +
                ", inUse=" + inUse +
                '}';
    }
}
