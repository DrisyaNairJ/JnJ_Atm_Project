package com.atm.poc.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.atm.poc.models.Account;
import com.atm.poc.models.TellerResponse;
import com.atm.poc.services.interfaces.IAccountsService;
import com.atm.poc.services.interfaces.IBankService;
import com.atm.poc.services.interfaces.ICashDispenserService;
import com.atm.poc.services.interfaces.ITellerService;

@SpringBootTest
@ActiveProfiles("test")
public class TellerServiceTests {

	private ICashDispenserService _cashDespenserService;
	private IAccountsService _accountsService;
	private IBankService _bankService;

	@Before
	public void setupMock() {
		_accountsService = mock(IAccountsService.class);
		_bankService = mock(IBankService.class);
		_cashDespenserService = mock(ICashDispenserService.class);
	}

	@Test
	public void whithdraw_should_return_error_message_when_invalid_account_number_is_requested() throws CustomExceptionHandler {
		Mockito.when(_accountsService.getAccount("INVALID_ACC_NO", 1234)).thenReturn(null);

		ITellerService sut = new TellerService(_cashDespenserService, _accountsService, _bankService);
		TellerResponse result = sut.withdraw("INVALID_ACC_NO", 1234, 100);
		assertEquals("Invalid credentials or account does not exist.", result.getMessage());
	}

	@Test
	public void whithdraw_should_return_error_message_when_requestedAmount_is_exceeded() throws CustomExceptionHandler {
		Mockito.when(_accountsService.getAccount("123456789", 1234)).thenReturn(new Account());

		ITellerService sut = new TellerService(_cashDespenserService, _accountsService, _bankService);
		TellerResponse result = sut.withdraw("123456789", 1234, 1800);
		assertEquals("Insufficient credit.", result.getMessage());
	}

	@Test
	public void whithdraw_should_return_error_message_when_available_funds_is_less_than_requested() throws CustomExceptionHandler {
		Account mockAccount = new Account();
		mockAccount.setBalance(1000);
		Mockito.when(_accountsService.getAccount("123456789", 1234)).thenReturn(mockAccount);
		Mockito.when(_bankService.getTotalFunds()).thenReturn(100);

		ITellerService sut = new TellerService(_cashDespenserService, _accountsService, _bankService);
		TellerResponse result = sut.withdraw("123456789", 1234, 200);
		assertEquals("Requested amount cannot be processed.", result.getMessage());
	}

	@Test
	public void whithdraw_should_call_bankService_to_update_funds_when_withdrawal_is_successful() throws CustomExceptionHandler {
		Account mockAccount = new Account();
		mockAccount.setBalance(1000);

		Mockito.when(_accountsService.getAccount("123456789", 1234)).thenReturn(mockAccount);
		Mockito.when(_bankService.getTotalFunds()).thenReturn(1000);

		ITellerService sut = new TellerService(_cashDespenserService, _accountsService, _bankService);
		TellerResponse result = sut.withdraw("123456789", 1234, 200);
		verify(_bankService).updateAvailabeFunds(200, result);
		verify(_cashDespenserService).dispense(200);
		assertEquals("200 dispensed.", result.getMessage());
	}
}