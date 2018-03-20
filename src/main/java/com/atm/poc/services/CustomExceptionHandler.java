package com.atm.poc.services;

import org.springframework.http.HttpStatus;

public class CustomExceptionHandler extends Exception{
	private String _message;
	private HttpStatus _status;
	
	public CustomExceptionHandler() {
	}
	
	// TODO :: better error handling needs to be done.
	public CustomExceptionHandler(String message, HttpStatus status){
		this._message = message;
		this._status = status;
	}	

	public String getMessage() {
		return _message;
	}

	public HttpStatus getStatus() {
		return _status;
	}
}
