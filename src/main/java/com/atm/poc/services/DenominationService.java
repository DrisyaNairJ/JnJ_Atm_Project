package com.atm.poc.services;

import java.util.HashMap;
import java.util.Map;

import com.atm.poc.models.Currency;
import com.atm.poc.services.interfaces.IBankService;
import com.atm.poc.services.interfaces.IDenominationService;

public class DenominationService implements IDenominationService {
	private IBankService _bankService;
	private Map<Integer, Currency> _money;

	public DenominationService(IBankService bankService) {
		this._bankService = bankService;
	}

	@Override
	public Map<Integer, Currency> getDenominations(int amount) {
		_money = new HashMap<Integer, Currency>();

		if (amount == 0) {
			return _money;
		}

		if (_bankService.getTotalFunds() < amount) {
			return _money;
		}

		int remainderAfter50 = this.getReminder(amount, 50);
		int denominations50 = this.getDenomination(amount, remainderAfter50);

		Map<Integer, Currency> availableDenominations = _bankService.getAvailableDenominations();
		int available50s = availableDenominations.get(50).getCount();

		if (remainderAfter50 > 0) {
			// checking whether the currency denomination is available in the
			// bank.
			if (available50s >= denominations50) {
				this._money.put(50, this.getCurrency(denominations50));
			} else {
				// TODO :: Go to next denomination.
			}
		}

		return _money;
	}

	private int getDenomination(int amount, int reminder) {
		return (amount - reminder) / reminder;
	}

	private int getReminder(int amount, int currency) {
		return (amount - currency) % currency;
	}

	private Currency getCurrency(int denomination) {
		Currency currency = new Currency();
		currency.setCount(denomination);

		return currency;
	}
}