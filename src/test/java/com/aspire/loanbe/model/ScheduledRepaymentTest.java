package com.aspire.loanbe.model;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.Test;

public class ScheduledRepaymentTest {

	@Test
	public void testConstructorAndGetters() {
		Loan loan = new Loan();

		Date repaymentDate = new Date();

		ScheduledRepayment scheduledRepayment = new ScheduledRepayment(loan, repaymentDate, 1000.0, StatusEnum.PENDING);
		scheduledRepayment.setId(1L);

		assertNotNull(scheduledRepayment.getId());
		assertEquals(loan, scheduledRepayment.getLoan());
		assertEquals(repaymentDate, scheduledRepayment.getRepaymentDate());
		assertEquals(1000.0, scheduledRepayment.getAmount());
		assertEquals(StatusEnum.PENDING, scheduledRepayment.getState());
	}

	@Test
	public void testEmptyConstructor() {

		ScheduledRepayment scheduledRepayment = new ScheduledRepayment();

		assertNull(scheduledRepayment.getLoan());
		assertNull(scheduledRepayment.getRepaymentDate());
		assertEquals(0.0, scheduledRepayment.getAmount());
		assertNull(scheduledRepayment.getState());
	}

	@Test
	public void testGettersAndSetters() {

		Loan loan = new Loan();
		loan.setId(1L);

		Date date = new Date();

		ScheduledRepayment scheduledRepayment = new ScheduledRepayment();

		scheduledRepayment.setId(1L);
		scheduledRepayment.setLoan(loan);
		scheduledRepayment.setRepaymentDate(date);
		scheduledRepayment.setAmount(1000.0);
		scheduledRepayment.setState(StatusEnum.PENDING);

		assertEquals(1L, scheduledRepayment.getId());
		assertEquals(loan, scheduledRepayment.getLoan());
		assertEquals(date, scheduledRepayment.getRepaymentDate());
		assertEquals(1000.0, scheduledRepayment.getAmount());
		assertEquals(StatusEnum.PENDING, scheduledRepayment.getState());
	}
}
