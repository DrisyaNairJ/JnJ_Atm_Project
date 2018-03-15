package com.atm.poc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.atm.poc.services.TellerService;
import com.atm.poc.services.interfaces.IAccountsService;
import com.atm.poc.services.interfaces.ICashDispenserService;
import com.atm.poc.services.interfaces.IDisplayService;
import com.atm.poc.services.interfaces.ITellerService;

@RestController
@RequestMapping("/api/v1/account")
public class AccountContoller {
	
	@Autowired
	IDisplayService displayService;
	
	@Autowired
	ICashDispenserService cashDispenserService;
	
	@Autowired
	IAccountsService accountsService;
	
	@RequestMapping(value = "/balance", method = RequestMethod.GET)
	public double getBalance(String accountNumber) {
		ITellerService teller = new TellerService(cashDispenserService, displayService, accountsService);
		return teller.checkBalance(accountNumber);
	}

	@RequestMapping(value = "/withdraw", method = RequestMethod.POST)
	public void withdrawAmount(String accountNumber, int amount) {
		ITellerService teller = new TellerService(cashDispenserService, displayService, accountsService);
		teller.withdraw(accountNumber, amount);
	}
}
