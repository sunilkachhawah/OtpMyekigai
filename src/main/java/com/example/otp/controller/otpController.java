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
    public ResponseEntity<String> generateOtp(@RequestBody otp otpRequest) {
        String generatedOtp = otpService.generateOtp(otpRequest.getNumber());
        return new ResponseEntity<>(generatedOtp, HttpStatus.OK);
    }

    @PostMapping("/validate")
    public ResponseEntity<String> validateOtp(@RequestBody otp otpRequest) {
        boolean isValid = otpService.validateOtp(otpRequest.getNumber(), otpRequest.getOtp());
        if (isValid) {
            return new ResponseEntity<>("OTP is valid", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid OTP", HttpStatus.BAD_REQUEST);
        }
    }
}
