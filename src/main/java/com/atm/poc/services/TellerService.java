package com.atm.poc.services;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.atm.poc.models.Account;
import com.atm.poc.models.Currency;
import com.atm.poc.models.TellerResponse;
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
	public TellerResponse withdraw(String accountNumber, int pinCode, int requestedAmount)
			throws CustomExceptionHandler {

		TellerResponse trResponse = new TellerResponse();
		Account account = this._accountsService.getAccount(accountNumber, pinCode);
		if (account == null) {
			trResponse.setMessage("Invalid credentials or account does not exist.");
			return trResponse;
		}

		if (!this.validateAccountBalance(account, requestedAmount)) {
			trResponse.setMessage("Insufficient credit.");
			return trResponse;
		}

		if (!this.validateAvailableFunds(requestedAmount)) {
			trResponse.setMessage("Requested amount cannot be processed.");
			return trResponse;
		}

		DenominationService denominationService = new DenominationService(this._bankService);
		Map<Integer, Currency> requestedDenominations = denominationService.getDenominations(requestedAmount);

		if (requestedDenominations != null && !requestedDenominations.isEmpty()) {
			
			Currency requested50 = requestedDenominations.get(50);
			if(requested50 != null){
				trResponse.setDenominationsOf50(requested50.getCount());
			}
			
			Currency requested20 = requestedDenominations.get(20);
			if(requested20 != null){
				trResponse.setDenominationsOf20(requested20.getCount());
			}
			
			Currency requested10 = requestedDenominations.get(10);
			if(requested10 != null){
				trResponse.setDenominationsOf10(requested10.getCount());
			}
			
			Currency requested5 = requestedDenominations.get(5);
			if(requested5 != null){
				trResponse.setDenominationsOf5(requested5.getCount());
			}
		}

		this._bankService.updateAvailabeFunds(requestedAmount, trResponse);
		account.withdraw(requestedAmount);
		this._cashDespenserService.dispense(requestedAmount);
		this.print("Withdrawal successful.");

		trResponse.setBalance(account.getBalance());
		trResponse.setMessage(requestedAmount + " dispensed.");
		return trResponse;
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

		return "Available balance is " + account.getBalance();
	}

	@Override
	public void print(String message) {
		// TODO : can be used for receipt printing.
	}

	private boolean validateAccountBalance(Account account, int requestedAmount) {
		if (account.getBalance() < requestedAmount) {
			this.print("Insufficient credit!");
			return false;
		}

		return true;
	}

	private boolean validateAvailableFunds(int requestedAmount) {
		int availableFunds = this._bankService.getTotalFunds();

		if (availableFunds < requestedAmount) {
			return false;
		}

		return true;
	}
}