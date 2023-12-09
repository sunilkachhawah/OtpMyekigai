package com.example.otp.service;

// all imports
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.otp.dao.OtpDao;
import com.example.otp.model.Contact;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class otpService {
	@Autowired
    private OtpDao otpDao;
	String otpGenerated;
	
	
    public String generateOtp(String number) {
    	Logger logger = LoggerFactory.getLogger(otpService.class);
        Random random = new Random();
//        logger.info("Generated OTP }: {}", otpGenerated);
        otpGenerated = String.format("%04d", random.nextInt(10000));

        Contact otpRequest = new Contact();
        otpRequest.setNumber(number);
        
        if(otpDao.findByNumber(number)!=null) {
        	logger.info("number already exist");
        }else {
        	otpDao.save(otpRequest);
        }
//        logger for print on console
        logger.info("Generated OTP for {}: {}", number, otpGenerated);
        return "Otp Sent Successfully";	
    }

    public boolean validateOtp(String otp) {
    	
    	Logger logger = LoggerFactory.getLogger(otpService.class);
    	System.out.println("This message will be printed to the console.");
    	logger.info("{}",otp);
    	if(otp.equals(otpGenerated)) {
    		return true;
    	}
    	else {
    		return false;
    	}       
    }
}
