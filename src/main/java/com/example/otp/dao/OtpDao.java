package com.example.otp.dao;

import com.example.otp.model.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface OtpDao extends MongoRepository<Contact, String> {
	Contact findByNumber(String number);
}
