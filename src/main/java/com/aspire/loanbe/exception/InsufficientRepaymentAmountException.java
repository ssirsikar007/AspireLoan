package com.aspire.loanbe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InsufficientRepaymentAmountException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InsufficientRepaymentAmountException(String message) {
		super(message);
	}
}
