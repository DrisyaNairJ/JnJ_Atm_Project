package com.atm.poc.services;

import java.util.HashMap;
import java.util.Map;

import com.atm.poc.models.Account;
import com.atm.poc.models.Currency;
import com.atm.poc.services.interfaces.IBankService;

public class BankService implements IBankService{
	private Map<Integer,Currency> _currency;
	
	public BankService(){
		this.initializeFunds();
	}

	// TODO :: Funds needs to be loaded from database.
	private void initializeFunds() {
		this._currency = new HashMap<Integer, Currency>();

		Currency currency = new Currency();
		currency.setCount(10);
		this._currency.put(5, currency);

		currency = new Currency();
		currency.setCount(10);
		this._currency.put(10, currency);
	}

	@Override
	public int GetTotalBalanceAvailable() {
		// TODO Auto-generated method stub
		return 0;
	}	
}