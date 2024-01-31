package com.proj.flightreservation.models;

import jakarta.persistence.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.math.BigDecimal;

@Entity
@CrossOrigin
@Table(name = "Seat")
public class Seat {

    @Id
    @Column(name = "SeatNumber", nullable = false)
    private String seatNumber;

    @Column(name = "FlightID", nullable = false)
    private int flightID;

    @Column(name = "SeatType", nullable = false, length = 20)
    private String seatType;

    @Column(name = "Price", nullable = false)
    private BigDecimal price;

    // Constructors
    public Seat() {
    }

    public Seat(String seatNumber, int flightID, String seatType, BigDecimal price) {
        this.seatNumber = seatNumber;
        this.flightID = flightID;
        this.seatType = seatType;
        this.price = price;
    }

    // Getters and Setters
    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public int getFlightID() {
        return flightID;
    }

    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
