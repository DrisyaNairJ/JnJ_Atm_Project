package com.atm.poc.services;

import org.springframework.stereotype.Service;

import com.atm.poc.services.interfaces.IPrinterService;

@Service
public class PrinterService implements IPrinterService{

	@Override
	public void print(String message) {
		// TODO :: Configure for receipt printing.		
	}	
}