package com.atm.poc.services;

import org.springframework.stereotype.Service;

import com.atm.poc.services.interfaces.IAuthenticationService;

@Service
public class AuthenticationService implements IAuthenticationService{

	@Override
	public boolean Authenticate(String accountNumber, int pinCode) {
		// TODO Auto-generated method stub
		return false;
	}
}
