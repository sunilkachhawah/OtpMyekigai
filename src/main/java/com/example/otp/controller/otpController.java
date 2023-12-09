package com.example.otp.controller;

import com.example.otp.service.*;
import com.example.otp.model.*;

import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("otp")
public class otpController {
    @Autowired
    private otpService otpService;

    @PostMapping("/generate")
    public ResponseEntity<String> generateOtp(@RequestBody Contact contact) {
        String generatedOtp = otpService.generateOtp(contact.getNumber());
        return new ResponseEntity<>(generatedOtp, HttpStatus.OK);
    }

    @PostMapping("/validate")
    public ResponseEntity<String> validateOtp(@RequestBody otp otp ) {
        boolean isValid = otpService.validateOtp(otp.getOtp());
        
        if (isValid) {
            return new ResponseEntity<>("OTP is valid", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid OTP", HttpStatus.BAD_REQUEST);
        }
    }
}
