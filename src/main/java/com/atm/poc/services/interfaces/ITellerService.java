package com.atm.poc.services.interfaces;

import com.atm.poc.models.TellerResponse;
import com.atm.poc.services.CustomExceptionHandler;

public interface ITellerService {

	public TellerResponse withdraw(String accountNumber, int pinCode, int amount) throws CustomExceptionHandler;

	public String checkBalance(String accountNumber, int pinCode);
}