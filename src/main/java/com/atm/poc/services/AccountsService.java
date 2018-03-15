package com.atm.poc.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.atm.poc.models.Account;
import com.atm.poc.services.interfaces.IAccountsService;

@Service
public class AccountsService implements IAccountsService {

	private Map<String, Account> _accounts;

	public AccountsService() {
		this.LoadAccounts();
	}

	@Override
	public Account getAccount(String accountNumber, int pinCode) {
		Account account = this._accounts.get(accountNumber);

		if (account != null && account.getPin() == pinCode) {
			return account;
		}

		return null;
	}

	// TODO :: Accounts needs to be loaded from database.
	private void LoadAccounts() {

		this._accounts = new HashMap<String, Account>();

		Account account = new Account();
		account.setAccountNumber("123456789");
		account.setPin(1234);
		account.setBalance(800);
		account.setOverDraft(200);
		this._accounts.put(account.getAccountNumber(), account);

		account = new Account();
		account.setAccountNumber("987654321");
		account.setPin(4321);
		account.setBalance(1230);
		account.setOverDraft(150);
		this._accounts.put(account.getAccountNumber(), account);
	}
}