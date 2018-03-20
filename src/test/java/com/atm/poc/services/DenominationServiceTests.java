package com.atm.poc.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.atm.poc.models.Currency;
import com.atm.poc.services.interfaces.IBankService;
import com.atm.poc.services.interfaces.IDenominationService;

@SpringBootTest
public class DenominationServiceTests {
	private IBankService _bankService;

	@Before
	public void setupMock() {
		_bankService = mock(IBankService.class);
	}

	@Test(expected = CustomExceptionHandler.class)
	public void getDenominations_throws_CustomException_when_input_isZero() throws CustomExceptionHandler {
		IDenominationService sut = new DenominationService(_bankService);

		sut.getDenominations(0);
	}

	@Test(expected = CustomExceptionHandler.class)
	public void getDenominations_returns_empty_map_when_total_available_fund_is_less_than_requested_amount()
			throws CustomExceptionHandler {
		Mockito.when(_bankService.getTotalFunds()).thenReturn(100);
		IDenominationService sut = new DenominationService(_bankService);

		sut.getDenominations(200);
	}

	@Test
	public void getDenominations_returns_map_with_values_when_bank_has_50_denominations_available()
			throws CustomExceptionHandler {
		Mockito.when(_bankService.getTotalFunds()).thenReturn(500);

		Map<Integer, Currency> mockAvailableDenominations = new HashMap<Integer, Currency>();
		Currency currency = new Currency();
		currency.setCount(20);
		mockAvailableDenominations.put(50, currency);

		Mockito.when(_bankService.getAvailableDenominations()).thenReturn(mockAvailableDenominations);
		IDenominationService sut = new DenominationService(_bankService);

		Map<Integer, Currency> result = sut.getDenominations(200);

		assertEquals(true, result.containsKey(50));
		assertEquals(4, result.get(50).getCount());
	}

	@Test
	public void getDenominations_returns_map_as_empty_when_bank_has_no_denominations_are_available()
			throws CustomExceptionHandler {
		Mockito.when(_bankService.getTotalFunds()).thenReturn(500);

		Map<Integer, Currency> mockAvailableDenominations = new HashMap<Integer, Currency>();
		Currency currency = new Currency();
		currency.setCount(0);
		mockAvailableDenominations.put(50, currency);

		Mockito.when(_bankService.getAvailableDenominations()).thenReturn(mockAvailableDenominations);
		IDenominationService sut = new DenominationService(_bankService);

		Map<Integer, Currency> result = sut.getDenominations(200);

		assertEquals(Collections.EMPTY_MAP, result);
	}

	@Test
	public void getDenominations_returns_map_with_null_50_denominations_when_bank_has_no_50_denominations_are_available()
			throws CustomExceptionHandler {
		Mockito.when(_bankService.getTotalFunds()).thenReturn(500);

		Map<Integer, Currency> mockAvailableDenominations = new HashMap<Integer, Currency>();
		Currency currency = new Currency();
		currency.setCount(10);
		mockAvailableDenominations.put(20, currency);

		currency = new Currency();
		currency.setCount(0);
		mockAvailableDenominations.put(50, currency);

		Mockito.when(_bankService.getAvailableDenominations()).thenReturn(mockAvailableDenominations);
		IDenominationService sut = new DenominationService(_bankService);

		Map<Integer, Currency> result = sut.getDenominations(200);

		assertEquals(null, result.get(50));
	}

	@Test
	public void getDenominations_returns_correct_map_when_215_is_passed_as_argument() throws CustomExceptionHandler {
		Mockito.when(_bankService.getTotalFunds()).thenReturn(2000);

		Mockito.when(_bankService.getAvailableDenominations()).thenReturn(this.initializeAtm());
		IDenominationService sut = new DenominationService(_bankService);

		Map<Integer, Currency> result = sut.getDenominations(215);

		assertEquals(4, result.get(50).getCount());
		assertEquals(1, result.get(10).getCount());
		assertEquals(1, result.get(5).getCount());
	}

	@Test(expected = CustomExceptionHandler.class)
	public void getDenominations_throwsCustomException_when_unsupported_denomination_is_passed_as_argument()
			throws CustomExceptionHandler {
		Mockito.when(_bankService.getTotalFunds()).thenReturn(2000);

		Mockito.when(_bankService.getAvailableDenominations()).thenReturn(this.initializeAtm());
		IDenominationService sut = new DenominationService(_bankService);

		Map<Integer, Currency> result = sut.getDenominations(101);

		assertEquals(Collections.EMPTY_MAP, result);
	}

	private Map<Integer, Currency> initializeAtm() {
		Map<Integer, Currency> mockAvailableDenominations = new HashMap<Integer, Currency>();
		Currency currency = new Currency();
		currency.setCount(20);
		mockAvailableDenominations.put(5, currency);

		currency = new Currency();
		currency.setCount(30);
		mockAvailableDenominations.put(10, currency);

		currency = new Currency();
		currency.setCount(30);
		mockAvailableDenominations.put(20, currency);

		currency = new Currency();
		currency.setCount(20);
		mockAvailableDenominations.put(50, currency);

		return mockAvailableDenominations;
	}
}
