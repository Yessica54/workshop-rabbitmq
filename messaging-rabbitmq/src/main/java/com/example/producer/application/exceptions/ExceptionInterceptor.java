package com.example.producer.application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.producer.application.vo.Response;

@ControllerAdvice
public class ExceptionInterceptor {
	
	@ExceptionHandler(AmountException.class)
	public final ResponseEntity<Object> handleExceptionAmount(AmountException ex) {
		Response exceptionResponse =
	        new Response(ex.getCode() , ex.getMessage(), ex.getUuid());
	    return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
}
