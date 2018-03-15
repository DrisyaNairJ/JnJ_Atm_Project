package com.atm.poc.services;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.atm.poc.models.Account;
import com.atm.poc.services.interfaces.IAccountsService;

@SpringBootTest
public class AccountsServiceTests {
	
	@Test
	public void getAccount_should_return_null_when_invalid_account_number_is_requested() {
		IAccountsService sut = new AccountsService();
		Account result = sut.getAccount("INVALID_ACC_NO", 1234);

		assertEquals(null, result);
	}
	
	@Test
	public void getAccount_should_return_null_when_incorrect_pinCode_is_supplied() {
		IAccountsService sut = new AccountsService();
		Account result = sut.getAccount("123456789", 0000);

		assertEquals(null, result);
	}
	
	@Test
	public void getAccount_should_return_account_when_account_number_and_pin_are_correct() {
		IAccountsService sut = new AccountsService();
		Account result = sut.getAccount("123456789", 1234);

		assertEquals("123456789", result.getAccountNumber());
		assertEquals(1234, result.getPin());
	}
}