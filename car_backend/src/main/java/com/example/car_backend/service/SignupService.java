package com.example.car_backend.service;

import java.sql.Blob;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Service;

import com.example.car_backend.model.UserDetails;
import com.example.car_backend.repository.SignupRepo;

@Service
public class SignupService {

    // Constructor Injection is the best way
    private SignupRepo signupRepo;

    public SignupService(SignupRepo signupRepo) {
        this.signupRepo = signupRepo;
    }

    @Autowired
    private MailService mailService;

    public void saveUser(UserDetails userDetails) {
        userDetails.setVerificationCode(mailService.generateVerificationCode());
        try {
            signupRepo.save(userDetails);
            mailService.sendSignupMail(userDetails.getEmail(), userDetails.getVerificationCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean verifyUser(String email, String otp) {
        UserDetails user = signupRepo.findById(email).get();
        if (user.getVerificationCode() == Integer.parseInt(otp)) {
            user.setVerified(true);
            signupRepo.save(user);
            mailService.otpVerifiedEmail(email);
            return true;
        }
        else
            return false;

    }

    public List<UserDetails> getUsers() {
        return signupRepo.findAll();
    }

    public Blob getImage(String id) {
        UserDetails user = signupRepo.findById(id).orElseGet(null);
        return user.getIdCard();
    }
}
