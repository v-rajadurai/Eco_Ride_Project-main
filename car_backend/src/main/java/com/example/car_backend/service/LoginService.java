package com.example.car_backend.service;

import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.example.car_backend.model.UserDetails;
import com.example.car_backend.repository.LoginRepo;

@Service
public class LoginService {
    LoginRepo loginRepo;

    LoginService(LoginRepo loginRepo) {
        this.loginRepo = loginRepo;
    }

    public UserDetails checkLoginCredentials(String email) {
        UserDetails user = loginRepo.findByEmail(email);
        if(user != null) {
            return user;
        }
        else {
            return new UserDetails();
        }
    }
}
