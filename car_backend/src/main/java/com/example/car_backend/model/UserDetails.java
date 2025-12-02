package com.example.car_backend.model;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;
import java.util.*;

import org.springframework.boot.context.properties.bind.DefaultValue;

import com.example.car_backend.model.userRideHistory.UserRideHistory;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
@Entity
public class UserDetails {
    private String firstName;
    private String lastName;

    @Id
    private String email;
    private String password;
    private String phoneNumber;
    private String department;
    private String yearOfStudy;
    private String licenceId;
    private String registerNumber;
    private int verificationCode;
    private boolean isVerified = false;
    
    @JsonIgnore
    @Lob
    private Blob idCard;
    private transient String encodedImage;

    @OneToMany(mappedBy = "userDetails", cascade = CascadeType.ALL)
    @JsonBackReference("userDetails-userRideHistory")
    private List<UserRideHistory> userRideHistory;
    
    // Constructors, getters, and setters
    public UserDetails() {
    }

    public UserDetails(String firstName, String lastName, String email, String password, String phoneNumber,
    String department, String yearOfStudy, String licenceId, String registerNumber, int verificationCode,
    boolean isVerified, Blob idCard, String encodedImage) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.department = department;
        this.yearOfStudy = yearOfStudy;
        this.licenceId = licenceId;
        this.registerNumber = registerNumber;
        this.verificationCode = verificationCode;
        this.isVerified = isVerified;
        this.idCard = idCard;
        this.encodedImage = encodedImage;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getLicenceId() {
        return licenceId;
    }

    public void setLicenceId(String licenceId) {
        this.licenceId = licenceId;
    }

    public String getRegisterNumber() {
        return registerNumber;
    }

    public void setRegisterNumber(String registerNumber) {
        this.registerNumber = registerNumber;
    }

    public int getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(int verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(String yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    public Blob getIdCard() {
        return idCard;
    }

    public void setIdCard(Blob idCard) {
        this.idCard = idCard;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }

    public String encodedImage() {
        if(idCard != null) {
            try {
                byte[] bytes = idCard.getBytes(1, (int) idCard.length());
                return Base64.getEncoder().encodeToString(bytes);
            } catch(SQLException e) {
                e.printStackTrace();
            } 
        }
        return null;
    }

    public String getEncodedImage() {
        return encodedImage;
    }
    public void setEncodedImage(String encodedImage) {
        this.encodedImage = encodedImage;
    }
}
