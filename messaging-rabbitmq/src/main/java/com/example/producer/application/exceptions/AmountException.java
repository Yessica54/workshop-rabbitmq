package com.example.producer.application.exceptions;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AmountException extends Exception{
	
	private String message;
	private String code;
	private UUID uuid;
	
	public AmountException() {
		super();
	}
	
	public AmountException(String message, String code, UUID uuid) {
		super(message);
		this.message = message;
		this.code = code;
		this.uuid = uuid;
	}
	
	public AmountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AmountException(String message, Throwable cause) {
		super(message, cause);
	}

	public AmountException(String message) {
		super(message);
	}

	public AmountException(Throwable cause) {
		super(cause);
	}
}
