package com.atm.poc.services.interfaces;


public interface ITellerService {

	public String withdraw(String accountNumber, int pinCode, int amount);

	public String checkBalance(String accountNumber, int pinCode);
}