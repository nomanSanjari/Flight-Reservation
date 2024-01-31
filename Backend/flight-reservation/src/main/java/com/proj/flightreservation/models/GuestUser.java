// src/main/java/com/sample/example/model/GuestUser.java
package com.proj.flightreservation.models;

import jakarta.persistence.*;

@Entity
@Table(name = "GuestUser")
public class GuestUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GuestUserID", nullable = false)
    private int GuestUserID;

    @Column(name = "UserID", unique = true, nullable = false)
    private int UserID;

    // Add other guest user-related fields as needed

    public GuestUser() {
    }

    public GuestUser(int userID) {
        UserID = userID;
        // Initialize other guest user-related fields as needed
    }

    @Override
    public String toString() {
        return GuestUserID + " -> " + UserID;
    }

    public void setUserID(int userID) {
        this.UserID = userID;
    }

}
