package com.atm.poc.services.interfaces;

import com.atm.poc.models.Account;

public interface ITellerService {
	void validate(Account account);

	void withdraw(String accountNumber, int amount);

	double checkBalance(String accountNumber);
}