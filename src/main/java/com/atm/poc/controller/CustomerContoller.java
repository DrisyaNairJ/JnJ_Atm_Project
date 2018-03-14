package com.atm.poc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.atm.poc.model.Customer;
import com.atm.poc.service.AuthenticationService;

@RestController
@RequestMapping("/api/v1")
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
		
		AuthenticationService service = new AuthenticationService();
		boolean validCustomer = service.authenticateCustomer(customer);
		
		if(validCustomer){
			//To-do
		}
		
		return customer;
	}
	
//	
//	@RequestMapping(value="/pin", method=RequestMethod.GET)
//	public String validateCustomer(){
//		return "Drisya";
//	}

}
