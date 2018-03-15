package com.atm.poc.services.interfaces;

public interface IAuthenticationService {
	public boolean Authenticate(String accountNumber, int pinCode);
}