package com.atm.poc.services.interfaces;

import java.util.Map;

import com.atm.poc.models.Currency;
import com.atm.poc.services.CustomExceptionHandler;

public interface IDenominationService{
	public Map<Integer, Currency> getDenominations(int amount) throws CustomExceptionHandler;
}