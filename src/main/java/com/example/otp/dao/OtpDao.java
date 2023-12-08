package com.example.otp.dao;

import com.example.otp.model.otp;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface OtpDao extends MongoRepository<otp, String> {
	otp findByNumber(String number);
}
