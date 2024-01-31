package com.proj.flightreservation.models;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "Booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingID;


    @Column(nullable = false)
    private int userID;

    @Column(nullable = false)
    private int flightID;

    @Column(nullable = false)
    private String seatNumber;

    @Column(nullable = false)
    private boolean insuranceSelected;

    @Column(nullable = false)
    private BigDecimal paymentAmount;

    @Column(nullable = false)
    private boolean isCancelled;

    public int getBookingID() {
        return bookingID;
    }

    public int getUserID() {
        return userID;
    }

    public int getFlightID() {
        return flightID;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public boolean isInsuranceSelected() {
        return insuranceSelected;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    // Setters
    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public void setInsuranceSelected(boolean insuranceSelected) {
        this.insuranceSelected = insuranceSelected;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public void setCancelled(boolean isCancelled) {
        this.isCancelled = isCancelled;
    }

    // toString Method
    @Override
    public String toString() {
        return "Booking{" +
                "bookingID=" + bookingID +
                ", userID=" + userID +
                ", flightID=" + flightID +
                ", seatNumber='" + seatNumber + '\'' +
                ", insuranceSelected=" + insuranceSelected +
                ", paymentAmount=" + paymentAmount +
                ", isCancelled=" + isCancelled +
                '}';
    }
}
