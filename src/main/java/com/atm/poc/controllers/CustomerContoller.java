package com.atm.poc.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.atm.poc.models.Customer;
import com.atm.poc.services.AuthenticationService;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerContoller {
		
	@RequestMapping(value="/user", method=RequestMethod.GET)
	public String getUser(){
		return "Drisya";
	}	
	
	@RequestMapping(value="/validate", method=RequestMethod.GET)
	public Customer validateCustomer(){
		
		Customer customer = new Customer();
		customer.setPin(1234);
		customer.setAccountNumber("123456789");
		
		return customer;
	}
}
