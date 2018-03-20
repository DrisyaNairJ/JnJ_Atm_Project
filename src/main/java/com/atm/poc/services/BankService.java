package com.atm.poc.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.atm.poc.models.Currency;
import com.atm.poc.models.TellerResponse;
import com.atm.poc.services.interfaces.IBankService;

@Service
public class BankService implements IBankService {
	private Map<Integer, Currency> _money;
	private int _totalFunds = 0;

	public BankService() {
		this.initializeFunds();
		this.setTotalFunds();
	}

	@Override
	public void updateAvailabeFunds(int amount, TellerResponse trResponse) {
		this._totalFunds -= amount;
		this.updateAvailabeDenominations(trResponse);
	}

	@Override
	public int getTotalFunds() {
		return this._totalFunds;
	}

	@Override
	public Map<Integer, Currency> getAvailableDenominations() {
		return this._money;
	}

	private void updateAvailabeDenominations(TellerResponse trResponse) {
		Currency currency = this._money.get(50);

		if (currency != null && trResponse.getDenominationsOf50() > 0) {			
			this._money.get(50).setCount(currency.getCount() - trResponse.getDenominationsOf50());
		}

		currency = this._money.get(20);

		if (currency != null && trResponse.getDenominationsOf20() > 0) {
			this._money.get(20).setCount(currency.getCount() - trResponse.getDenominationsOf20());
		}

		currency = this._money.get(10);

		if (currency != null && trResponse.getDenominationsOf10() > 0) {
			this._money.get(10).setCount(currency.getCount() - trResponse.getDenominationsOf10());
		}

		currency = this._money.get(5);

		if (currency != null && trResponse.getDenominationsOf5() > 0) {
			this._money.get(5).setCount(currency.getCount() - trResponse.getDenominationsOf5());
		}
	}

	// TODO :: Funds needs to be loaded from database.
	private void initializeFunds() {
		this._money = new HashMap<Integer, Currency>();

		Currency currency = new Currency();
		currency.setCount(20);
		this._money.put(5, currency);

		currency = new Currency();
		currency.setCount(30);
		this._money.put(10, currency);

		currency = new Currency();
		currency.setCount(30);
		this._money.put(20, currency);

		currency = new Currency();
		currency.setCount(20);
		this._money.put(50, currency);
	}

	private int setTotalFunds() {
		if (this._money.size() == 0) {
			return 0;
		}

		this._money.forEach((k, v) -> {
			_totalFunds = _totalFunds + (k * v.getCount());
		});

		return _totalFunds;
	}
}