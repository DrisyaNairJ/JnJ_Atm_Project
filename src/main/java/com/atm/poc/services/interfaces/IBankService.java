package com.atm.poc.services.interfaces;

public interface IBankService{
	
	public void updateAvailabeFunds(int amount);
	
	public int getTotalFunds();
}