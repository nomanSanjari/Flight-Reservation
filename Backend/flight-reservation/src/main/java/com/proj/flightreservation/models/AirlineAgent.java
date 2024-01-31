// src/main/java/com/sample/example/model/AirlineAgent.java
package com.proj.flightreservation.models;

import jakarta.persistence.*;

@Entity
@Table(name = "AirlineAgent")
public class AirlineAgent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AirlineAgentID", nullable = false)
    private int AirlineAgentID;

    @Column(name = "UserID", unique = true, nullable = false)
    private int UserID;

    @Column(name = "Pwd_AirlineAgent", nullable = false)
    private String Pwd_AirlineAgent;


    public AirlineAgent() {
    }

    public AirlineAgent(int userID, String pwd_AirlineAgent) {
        this.UserID = userID;
        this.Pwd_AirlineAgent = pwd_AirlineAgent;
    }

    @Override
    public String toString() {
        return AirlineAgentID + " -> " + UserID + " -> " + Pwd_AirlineAgent;
    }

    public void setUserID(int userID) {
        this.UserID = userID;
    }


    public void setPassword(String password) {
        this.Pwd_AirlineAgent = password;
    }
}
