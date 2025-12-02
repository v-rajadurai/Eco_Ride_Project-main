package com.example.car_backend.controller;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.car_backend.model.UserDetails;
import com.example.car_backend.scripts.ImageRecognition;
import com.example.car_backend.service.SignupService;

@RestController
public class SignupController {

    private SignupService signupService;
    private String email;

    public SignupController(SignupService signupService) {
        this.signupService = signupService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@ModelAttribute UserDetails userDetails, @RequestParam("image") MultipartFile imageFile) {
        email = userDetails.getEmail();
        String name = userDetails.getLastName() + " " + userDetails.getFirstName();
        String register_number = email.replaceAll("@skcet.ac.in", "");
        System.out.println(register_number.toUpperCase() + " " + name.toUpperCase());
        ImageRecognition recognition = new ImageRecognition();
        try {
            if (!imageFile.isEmpty()) {
                // Convert MultipartFile to byte[]
                byte[] imageBytes = imageFile.getBytes();

                // Convert byte[] to Blob
                Blob imageBlob = new SerialBlob(imageBytes);

                userDetails.setIdCard(imageBlob);
                List<String> results = recognition.recognizeImage(imageFile);
                if(results.contains(register_number.toUpperCase()) && results.contains(name.toUpperCase())) {
                    signupService.saveUser(userDetails);
                    return new ResponseEntity<>("You have successfully added to database", HttpStatus.CREATED);
                } else {
                    return new ResponseEntity<>("We can't able to recoginze your ID card", HttpStatus.NOT_ACCEPTABLE);
                }

            } else {
                return new ResponseEntity<>("Image file is required", HttpStatus.BAD_REQUEST);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error processing image file", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Internal Error in database", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/verify/{otp}")
    public ResponseEntity<String> verify(@PathVariable String otp) {
        try {
            if (signupService.verifyUser(email, otp))
                return new ResponseEntity<String>("User verified", HttpStatus.ACCEPTED);
            else
                return new ResponseEntity<String>("User not verified", HttpStatus.NOT_ACCEPTABLE);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Internal Error in database", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/testing/getUsers")
    public List<UserDetails> getUsers() {
        return signupService.getUsers();
    }

    @GetMapping("/api/testing/image")
    public ResponseEntity<String> getImage(@RequestParam("id") String id) throws SQLException {
        Blob imageBlob = signupService.getImage(id);
        byte[] imageBytes = imageBlob.getBytes(1, (int) imageBlob.length());
        String base64Image = Base64.getEncoder().encodeToString(imageBytes);

        return ResponseEntity
                .ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(base64Image);
    }
}
