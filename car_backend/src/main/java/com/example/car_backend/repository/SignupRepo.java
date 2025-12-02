package com.example.car_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.car_backend.model.UserDetails;

@Repository
/**
 * SignupRepo
 */
public interface SignupRepo extends JpaRepository<UserDetails, String> {

}
