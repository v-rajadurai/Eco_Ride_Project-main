// package com.example.skcet_rapido.controller;
package com.example.car_backend.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.car_backend.model.UserDetails;
import com.example.car_backend.service.LoginService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/ecoride")
public class LoginController {
    LoginService loginService;

    LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public ResponseEntity<UserDetails> login(@RequestParam String email, @RequestParam String password) {
        UserDetails user = loginService.checkLoginCredentials(email);
        if(user != null && password != null && password.equals(user.getPassword())) {
            return ResponseEntity.ok(user);
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

}
