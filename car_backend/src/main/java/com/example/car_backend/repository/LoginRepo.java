package com.example.car_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.car_backend.model.UserDetails;

@Repository
public interface LoginRepo extends JpaRepository<UserDetails, String> {
   UserDetails findByEmail(String email);
}