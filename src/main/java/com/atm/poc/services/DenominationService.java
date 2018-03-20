package com.atm.poc.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

import com.atm.poc.models.Currency;
import com.atm.poc.services.interfaces.IBankService;
import com.atm.poc.services.interfaces.IDenominationService;

public class DenominationService extends CustomExceptionHandler implements IDenominationService {
	private IBankService _bankService;
	private Map<Integer, Currency> _money;

	public DenominationService(IBankService bankService) {
		this._bankService = bankService;
	}

	@Override
	public Map<Integer, Currency> getDenominations(int amount) throws CustomExceptionHandler {
		_money = new HashMap<Integer, Currency>();

		if (amount == 0 || amount % 5 != 0) {
			throw new CustomExceptionHandler("Sorry, this amount could not be dispensed.", HttpStatus.BAD_REQUEST);
		}

		if (_bankService.getTotalFunds() < amount) {
			throw new CustomExceptionHandler(
					"Sorry, this amount could not be dispensed, please try with a lesser amount.",
					HttpStatus.BAD_REQUEST);
		}

		Map<Integer, Currency> availableDenominationsInBank = this._bankService.getAvailableDenominations();

		while (amount >= 50) {
			int denominations50 = amount / 50;

			if (availableDenominationsInBank != null && !availableDenominationsInBank.isEmpty()) {

				Currency availableDenominations = availableDenominationsInBank.get(50);
				if (availableDenominations != null && availableDenominations.getCount() >= denominations50) {
					this._money.put(50, this.getCurrency(denominations50));
					amount = amount % 50;
				}
			}

			break;
		}
		
		while (amount >= 20) {
			int denominations20 = amount / 20;
			if (availableDenominationsInBank != null && !availableDenominationsInBank.isEmpty()) {

				Currency availableDenominations = availableDenominationsInBank.get(20);
				if (availableDenominations != null && availableDenominations.getCount() >= denominations20) {
					this._money.put(20, this.getCurrency(denominations20));
					amount = amount % 20;
				}
			}

			break;
		}
		
		while (amount >= 10) {
			int denominations10 = amount / 10;
			if (availableDenominationsInBank != null && !availableDenominationsInBank.isEmpty()) {

				Currency availableDenominations = availableDenominationsInBank.get(10);
				if (availableDenominations != null && availableDenominations.getCount() >= denominations10) {
					this._money.put(10, this.getCurrency(denominations10));
					amount = amount % 10;
				}
			}

			break;
		}

		while (amount >= 5) {
			int denominations5 = amount / 5;
			if (availableDenominationsInBank != null && !availableDenominationsInBank.isEmpty()) {

				Currency availableDenominations = availableDenominationsInBank.get(5);
				if (availableDenominations != null && availableDenominations.getCount() >= denominations5) {
					this._money.put(5, this.getCurrency(denominations5));
					amount = amount % 5;
				}
			}

			break;
		}

		return _money;
	}

	private Currency getCurrency(int denomination) {
		Currency currency = new Currency();
		currency.setCount(denomination);

		return currency;
	}
}