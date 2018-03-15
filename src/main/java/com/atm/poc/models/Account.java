package com.atm.poc.models;

public class Account {

	private String accountNumber;
	private int pin;
	private double balance;
	private double overDraft;

	public int getPin() {
		return this.pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public String getAccountNumber() {
		return this.accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getBalance() {
		return this.balance;
	}

	public void setBalance(double openingBalance) {
		this.balance = openingBalance;
	}

	public double getOverDraft() {
		return this.overDraft;
	}

	public void setOverDraft(double overDraft) {
		this.overDraft = overDraft;
	}

	public void withdraw(int requestedWithdrawal) {
		this.balance -= requestedWithdrawal;
	}

	public void credit(int amount) {
		this.balance += amount;
	}
}