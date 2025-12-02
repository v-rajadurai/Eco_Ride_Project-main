package com.example.car_backend.model.bookRide;

import com.example.car_backend.model.userRideHistory.UserRideHistory;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Data
@NoArgsConstructor
public class BookRide {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    private String name;
    private String phone;
    private String email;
    private String leaving;
    private String going;
    private int availableSeats;
    private double price;
    private String carName;
    private String carNumber;
    private String date;
    private String startTime;
    private String endTime;
    private String leavingFromLatitude;
    private String leavingFromLongitude;
    private String goingToLatitude;
    private String goingToLongitude;
    private String locationFirstName;
    private String goingLocationFirstName;
    private String rideCompletionStatus;
    private String distance;

    @OneToMany(mappedBy = "bookRide", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<UserRideHistory> userRideHistory;

}
