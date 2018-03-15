package com.atm.poc.services.interfaces;

import com.atm.poc.models.Account;

public interface IAccountsService {
	public Account GetAccountByAccountNumber(String accountNumber);
}