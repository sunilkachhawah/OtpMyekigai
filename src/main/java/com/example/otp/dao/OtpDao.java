package com.example.otp.dao;

import com.example.otp.model.Contact;

//it dao layer which extends MongoRepository 
// if we want to connect with Mysql datbase then extend JPA repository

import org.springframework.data.mongodb.repository.MongoRepository;
public interface OtpDao extends MongoRepository<Contact, String> {
//	query for find number in database
	Contact findByNumber(String number);
}
