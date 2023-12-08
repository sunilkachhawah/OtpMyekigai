package com.example.otp.service;

// all imports
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.otp.dao.OtpDao;
import com.example.otp.model.otp;

@Service
public class otpService {
	@Autowired
    private OtpDao otpDao;

    public String generateOtp(String number) {
        Random random = new Random();
        String otp = String.format("%04d", random.nextInt(10000));

        otp otpRequest = new otp();
        otpRequest.setNumber(number);
        otpRequest.setOtp(otp);

        otpDao.save(otpRequest);

        return otp;
    }

    public boolean validateOtp(String number, String otp) {
        otp savedOtpRequest = otpDao.findByNumber(number);
        return savedOtpRequest != null && savedOtpRequest.getOtp().equals(otp);
    }

}
