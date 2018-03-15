package com.atm.poc.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.atm.poc.models.Customer;
import com.atm.poc.services.AuthenticationService;
import com.atm.poc.services.interfaces.IAuthenticationService;
import com.atm.poc.services.interfaces.IDisplayService;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerContoller {

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String getUser() {
		return "Drisya";
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public boolean validateCustomer(String accountNumber, int pinCode) {
		
		IAuthenticationService service = new AuthenticationService();

		if (service.Authenticate(accountNumber, pinCode)) {
			return true;
		} else {
			return false;
		}
	}
}
