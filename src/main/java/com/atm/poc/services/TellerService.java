package com.atm.poc.services;

import org.springframework.stereotype.Service;

import com.atm.poc.models.Account;
import com.atm.poc.services.interfaces.IAccountsService;
import com.atm.poc.services.interfaces.ICashDispenserService;
import com.atm.poc.services.interfaces.IDisplayService;
import com.atm.poc.services.interfaces.ITellerService;

@Service
public class TellerService implements ITellerService, IDisplayService {

	private ICashDispenserService _cashDespenserService;
	private IDisplayService _displayService;
	private IAccountsService _accountsService;

	public TellerService(ICashDispenserService cashDespenserService, IDisplayService displayService,
			IAccountsService accountsService) {
		this._cashDespenserService = cashDespenserService;
		this._displayService = displayService;
		this._accountsService = accountsService;
	}

	@Override
	public void validate(Account account) {
		if (account == null) {
			this._displayService.showMessage("No account exists!");
			return;
		}

		// this._account = account;
	}

	@Override
	public void withdraw(String accountNumber, int amount) {

		Account account = this._accountsService.GetAccountByAccountNumber(accountNumber);
		if (account == null) {
			return;
		}
		
		if(!this.validateRequest(account, amount)){
			return;
		}		

		account.withdraw(amount);
		this._cashDespenserService.dispense(amount);
		this._displayService.showMessage("Withdrawal successful.");
	}

	@Override
	public double checkBalance(String accountNumber) {
		if (accountNumber.isEmpty()) {
			this._displayService.showMessage("No account exists!");
			return 0.00;
		}

		Account account = this._accountsService.GetAccountByAccountNumber(accountNumber);

		if (account == null) {
			this._displayService.showMessage("No account exists!");
			return 0.00;
		}

		return account.getBalance();
	}

	@Override
	public void showMessage(String message) {
		// TODO Auto-generated method stub
	}
	
	private boolean validateRequest(Account account, int amount) {
		if (account.getBalance() < amount) {
			this._displayService.showMessage("Insufficient credit!");
			return false;
		}
		
		if(!this.validateDenomination(amount)){
			return false;
		}
		
		return true;
	}
	
	private boolean validateDenomination(int amount) {
		
		
		return true;
	}
}