package com.atm.poc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.atm.poc.models.TellerResponse;
import com.atm.poc.services.BankService;
import com.atm.poc.services.CustomExceptionHandler;
import com.atm.poc.services.TellerService;
import com.atm.poc.services.interfaces.IAccountsService;
import com.atm.poc.services.interfaces.IBankService;
import com.atm.poc.services.interfaces.ICashDispenserService;
import com.atm.poc.services.interfaces.ITellerService;

@RestController
@RequestMapping("/api/v1/account")
public class AccountContoller {

	@Autowired
	ICashDispenserService cashDispenserService;

	@Autowired
	IAccountsService accountsService;

	@Autowired
	IBankService bankService;

	public AccountContoller() {
		bankService = new BankService();
	}

	@RequestMapping(value = "/balance", method = RequestMethod.GET)
	public String getBalance(String accountNumber, int pinCode) {
		ITellerService teller = new TellerService(cashDispenserService, accountsService, bankService);
		return teller.checkBalance(accountNumber, pinCode);
	}

	@RequestMapping(value = "/withdraw", method = RequestMethod.POST)
	public TellerResponse withdrawAmount(String accountNumber, int pinCode, int requestedAmount) throws CustomExceptionHandler {
		ITellerService teller = new TellerService(cashDispenserService, accountsService, bankService);
		return teller.withdraw(accountNumber, pinCode, requestedAmount);
	}
}
