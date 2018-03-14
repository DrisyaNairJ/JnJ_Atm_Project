package com.atm.poc.model;

public class Customer {
	
private String accountNumber;
private int pin;
private Account account;

public int getPin() {
	return pin;
}

public void setPin(int pin) {
	this.pin = pin;
}

public String getAccountNumber() {
	return accountNumber;
}

public void setAccountNumber(String accountNumber) {
	this.accountNumber = accountNumber;
}

public Account getAccount() {
	return account;
}

public void setAccount(Account account) {
	this.account = account;
}
	

}
