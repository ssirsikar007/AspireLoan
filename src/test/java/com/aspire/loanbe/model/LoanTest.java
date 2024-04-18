package com.aspire.loanbe.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

public class LoanTest {

	@Test
	public void testLoanConstructorWithParameters() {
		
		Member customer = new Member();

		
		List<ScheduledRepayment> scheduledRepayments = new ArrayList<>();
		scheduledRepayments.add(new ScheduledRepayment());

		
		Date date = new Date();

		
		Loan loan = new Loan(customer, 1000.0, 12, StatusEnum.PENDING, scheduledRepayments, date);

		
		assertNotNull(loan);
		assertEquals(customer, loan.getCustomer());
		assertEquals(1000.0, loan.getAmount());
		assertEquals(12, loan.getTerm());
		assertEquals(StatusEnum.PENDING, loan.getState());
		assertEquals(scheduledRepayments, loan.getScheduledRepayments());
		assertEquals(date, loan.getLoanDate());
	}

	@Test
	public void testLoanConstructorWithParametersTwo() {
		
		Member customer = new Member();

		
		Date date = new Date();

		Loan loan = new Loan(customer, 1000.0, 12, StatusEnum.PENDING, date);

		
		assertNotNull(loan);
		assertEquals(customer, loan.getCustomer());
		assertEquals(1000.0, loan.getAmount());
		assertEquals(12, loan.getTerm());
		assertEquals(StatusEnum.PENDING, loan.getState());
		assertEquals(date, loan.getLoanDate());
	}

	@Test
	public void testLoanConstructorWithoutParameters() {
		
		Loan loan = new Loan();

		
		assertNotNull(loan);
		assertNull(loan.getCustomer());
		assertEquals(0.0, loan.getAmount());
		assertEquals(0, loan.getTerm());
		assertNull(loan.getState());
		assertNull(loan.getScheduledRepayments());
		assertNull(loan.getLoanDate());
	}

	@Test
	public void testLoanSetterAndGetterMethods() {
		
		Loan loan = new Loan();

	
		Member customer = new Member();
		loan.setCustomer(customer);
		loan.setAmount(1500.0);
		loan.setTerm(24);
		loan.setState(StatusEnum.APPROVED);
		loan.setId(1L);
		List<ScheduledRepayment> scheduledRepayments = new ArrayList<>();
		loan.setScheduledRepayments(scheduledRepayments);
		Date date = new Date();
		loan.setLoanDate(date);

		
		assertEquals(customer, loan.getCustomer());
		assertEquals(1500.0, loan.getAmount());
		assertEquals(24, loan.getTerm());
		assertEquals(StatusEnum.APPROVED, loan.getState());
		assertEquals(scheduledRepayments, loan.getScheduledRepayments());
		assertEquals(date, loan.getLoanDate());
		assertEquals(1L, loan.getId());
	}
}
