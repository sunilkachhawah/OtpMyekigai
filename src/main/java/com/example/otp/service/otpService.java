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
	Logger logger = LoggerFactory.getLogger(otpService.class);
	@Autowired
    private OtpDao otpDao;
	String otpGenerated;
	
	public String random() {
		
        return otpGenerated;
	}
	
    public String generateOtp(String number) {
    	
        try {
        	Contact otpRequest = new Contact();
            otpRequest.setNumber(number);
            Random random = new Random();
            otpGenerated = String.format("%04d", random.nextInt(10000));
            if(otpDao.findByNumber(number)!=null) {
            	logger.info("number already exist");
            }else {
            	otpDao.save(otpRequest);
            }

            logger.info("Generated OTP for {}: {}", number, otpGenerated);
            return "Otp Sent Successfully";	
        }catch (Exception e) {
            logger.error("Error generating OTP: {}", e.getMessage());
            throw new RuntimeException("Error generating OTP");
        }
    }

    public boolean validateOtp(String otp) {
    	
    	try {
    		logger.info("{}",otp);
    		logger.info("you enetered :{}, {}", otp, otpGenerated);
        	if(otp.equals(otpGenerated)) {
        		return true;
        	}
        	else {
        		return false;
        	}       
    	}catch (Exception e) {
            logger.error("Error validating OTP: {}", e.getMessage());
            throw new RuntimeException("Error validating OTP");
        }
    }
}
