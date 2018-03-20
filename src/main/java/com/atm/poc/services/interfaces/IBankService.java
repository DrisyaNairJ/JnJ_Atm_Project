package com.atm.poc.services.interfaces;

import java.util.Map;

import com.atm.poc.models.Currency;
import com.atm.poc.models.TellerResponse;

public interface IBankService{
	
	public int getTotalFunds();
	
	public Map<Integer, Currency> getAvailableDenominations();

	void updateAvailabeFunds(int amount, TellerResponse trResponse);
}