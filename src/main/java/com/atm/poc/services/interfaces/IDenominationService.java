package com.atm.poc.services.interfaces;

import java.util.Map;

import com.atm.poc.models.Currency;

public interface IDenominationService{
	public Map<Integer, Currency> getDenominations(int amount);
}