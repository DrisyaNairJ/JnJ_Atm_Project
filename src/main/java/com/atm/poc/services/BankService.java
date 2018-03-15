package com.atm.poc.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.atm.poc.models.Currency;
import com.atm.poc.services.interfaces.IBankService;

@Service
public class BankService implements IBankService {
	private Map<Integer, Currency> _currency;
	private int _totalFunds = 0;

	public BankService() {
		this.initializeFunds();
		this.setTotalFunds();
	}
	
	@Override
	public void updateAvailabeFunds(int amount) {
		this._totalFunds -= amount;
	}
	
	@Override
	public int getTotalFunds() {
		return this._totalFunds;
	}

	// TODO :: Funds needs to be loaded from database.
	private void initializeFunds() {
		this._currency = new HashMap<Integer, Currency>();

		Currency currency = new Currency();
		currency.setCount(20);
		this._currency.put(5, currency);

		currency = new Currency();
		currency.setCount(30);
		this._currency.put(10, currency);

		currency = new Currency();
		currency.setCount(30);
		this._currency.put(20, currency);

		currency = new Currency();
		currency.setCount(20);
		this._currency.put(50, currency);
	}

	private int setTotalFunds() {
		if (this._currency.size() == 0) {
			return 0;
		}

		this._currency.forEach((k, v) -> {
			_totalFunds = _totalFunds + (k * v.getCount());
		});

		return _totalFunds;
	}	
}