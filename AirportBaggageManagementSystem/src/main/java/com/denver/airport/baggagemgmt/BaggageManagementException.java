package com.denver.airport.baggagemgmt;

public class BaggageManagementException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public BaggageManagementException(Exception cause) {
		super(cause);
	}	

	public BaggageManagementException(String message, Exception cause) {
		super(message,cause);
	}
	
	public BaggageManagementException(String messgae) {
		super(messgae);
	}
}

