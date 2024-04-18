package com.aspire.loanbe.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.aspire.loanbe.exception.CustomerNotFoundException;
import com.aspire.loanbe.exception.InsufficientRepaymentAmountException;
import com.aspire.loanbe.exception.InvalidLoanException;
import com.aspire.loanbe.exception.LoanCompletedException;
import com.aspire.loanbe.exception.LoanNotFoundException;
import com.aspire.loanbe.exception.NotAdminException;

@RestControllerAdvice
public class ApplicationExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex) {
		Map<String, String> erroMap = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error -> {
			erroMap.put(error.getField(), error.getDefaultMessage());
		});
		return erroMap;
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(CustomerNotFoundException.class)
	public Map<String, String> handleBusinessException(CustomerNotFoundException ex) {
		Map<String, String> erroMap = new HashMap<>();
		erroMap.put("error", ex.getMessage());
		return erroMap;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ NotAdminException.class, InsufficientRepaymentAmountException.class, InvalidLoanException.class,
			LoanCompletedException.class, LoanNotFoundException.class, LoanNotFoundException.class })
	public Map<String, String> handleInvalidArgument(Exception ex) {
		Map<String, String> erroMap = new HashMap<>();
		erroMap.put("error", ex.getMessage());
		return erroMap;

	}

}
