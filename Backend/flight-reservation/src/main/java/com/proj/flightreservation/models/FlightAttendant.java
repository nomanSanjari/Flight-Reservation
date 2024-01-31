package com.proj.flightreservation.models;

import jakarta.persistence.*;

@Entity
@Table(name = "FlightAttendant")
public class FlightAttendant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FlightAttendantID", nullable = false)
    private int FlightAttendantID;

    @Column(name = "UserID", unique = true, nullable = false)
    private int UserID;

    @Column(name = "Pwd_FlightAttendant", nullable = false)
    private String Pwd_FlightAttendant;

    public FlightAttendant() {

    }

    public FlightAttendant(int userID, String pwd_FlightAttendant) {
        UserID = userID;
        Pwd_FlightAttendant = pwd_FlightAttendant;
    }

    @Override
    public String toString() {
        return FlightAttendantID + " -> " + UserID + " -> " + Pwd_FlightAttendant;
    }

    public void setUserID(int userID) {
        this.UserID = userID;
    }

    public void setPassword(String password) {
        this.Pwd_FlightAttendant = password;
    }
}
