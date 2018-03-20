package com.atm.poc.models;

public class TellerResponse {
	private String message;
	private double balance;
	private int denominationsOf50;
	private int denominationsOf20;
	private int denominationsOf10;
	private int denominationsOf5;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getDenominationsOf50() {
		return denominationsOf50;
	}

	public void setDenominationsOf50(int denominationsOf50) {
		this.denominationsOf50 = denominationsOf50;
	}

	public int getDenominationsOf20() {
		return denominationsOf20;
	}

	public void setDenominationsOf20(int denominationsOf20) {
		this.denominationsOf20 = denominationsOf20;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getDenominationsOf10() {
		return denominationsOf10;
	}

	public void setDenominationsOf10(int denominationsOf10) {
		this.denominationsOf10 = denominationsOf10;
	}

	public int getDenominationsOf5() {
		return denominationsOf5;
	}

	public void setDenominationsOf5(int denominationsOf5) {
		this.denominationsOf5 = denominationsOf5;
	}
}
