package com.example.car_backend.model.userRideHistory;

import com.example.car_backend.model.UserDetails;
import com.example.car_backend.model.bookRide.BookRide;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class UserRideHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String status;

    @ManyToOne
    @JsonManagedReference("userDetails-userRideHistory")
    private UserDetails userDetails;

    @ManyToOne
    // @JsonManagedReference("bookRide-userRideHistory")
    private BookRide bookRide;
}
