package com.atm.poc.services.interfaces;

import java.util.Map;

import com.atm.poc.models.Currency;

public interface IBankService{
	
	public void updateAvailabeFunds(int amount);
	
	public int getTotalFunds();
	
	public Map<Integer, Currency> getAvailableDenominations();
}