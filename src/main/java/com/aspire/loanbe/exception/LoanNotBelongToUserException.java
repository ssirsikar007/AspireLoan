package com.aspire.loanbe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class LoanNotBelongToUserException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public LoanNotBelongToUserException(String message) {
		super(message);
	}
}
