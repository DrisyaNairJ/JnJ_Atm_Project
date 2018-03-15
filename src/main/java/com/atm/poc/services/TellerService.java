package com.atm.poc.services;

import org.springframework.stereotype.Service;

import com.atm.poc.models.Account;
import com.atm.poc.services.interfaces.IAccountsService;
import com.atm.poc.services.interfaces.IBankService;
import com.atm.poc.services.interfaces.ICashDispenserService;
import com.atm.poc.services.interfaces.IPrinterService;
import com.atm.poc.services.interfaces.ITellerService;

@Service
public class TellerService implements ITellerService, IPrinterService {

	private ICashDispenserService _cashDespenserService;
	private IAccountsService _accountsService;
	private IBankService _bankService;
	private static String invalidAccountMessage = "Invalid credentials or no account exists.";

	public TellerService(ICashDispenserService cashDespenserService, IAccountsService accountsService,
			IBankService bankService) {
		this._cashDespenserService = cashDespenserService;
		this._accountsService = accountsService;
		this._bankService = bankService;
	}

	@Override
	public String withdraw(String accountNumber, int pinCode, int requestedAmount) {

		Account account = this._accountsService.getAccount(accountNumber, pinCode);
		if (account == null) {
			return "Invalid credentials or account does not exist.";
		}

		if (!this.validateAccountBalance(account, requestedAmount)) {
			return "Insufficient credit";
		}

		if (!this.validateDenomination(requestedAmount)) {
			return "Requested amount cannot be processed.";
		}

		this._bankService.updateAvailabeFunds(requestedAmount);
		account.withdraw(requestedAmount);
		this._cashDespenserService.dispense(requestedAmount);
		this.print("Withdrawal successful.");

		return requestedAmount + " dispensed";
	}

	@Override
	public String checkBalance(String accountNumber, int pinCode) {
		if (accountNumber.isEmpty() || accountNumber == null) {
			this.print("No account exists!");
			return invalidAccountMessage;
		}

		Account account = this._accountsService.getAccount(accountNumber, pinCode);

		if (account == null) {
			this.print("No account exists!");
			return invalidAccountMessage;
		}

		return "Available balance is: " + account.getBalance();
	}

	@Override
	public void print(String message) {
		// TODO Auto-generated method stub
	}

	private boolean validateAccountBalance(Account account, int requestedAmount) {
		if (account.getBalance() < requestedAmount) {
			this.print("Insufficient credit!");
			return false;
		}

		return true;
	}

	private boolean validateDenomination(int requestedAmount) {
		int availableFunds = this._bankService.getTotalFunds();

		if (availableFunds < requestedAmount) {
			return false;
		}

		return true;
	}
}