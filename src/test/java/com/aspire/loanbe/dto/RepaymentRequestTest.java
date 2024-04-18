package com.aspire.loanbe.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RepaymentRequestTest {

	@Test
	public void testConstructorAndGetters() {

		RepaymentRequest repaymentRequest = new RepaymentRequest(1L, 1000.0);

		assertEquals(1L, repaymentRequest.getLoanId());
		assertEquals(1000.0, repaymentRequest.getAmount());
	}

	@Test
	public void testSetters() {

		RepaymentRequest repaymentRequest = new RepaymentRequest();

		repaymentRequest.setLoanId(2L);
		repaymentRequest.setAmount(2000.0);

		assertEquals(2L, repaymentRequest.getLoanId());
		assertEquals(2000.0, repaymentRequest.getAmount());
	}
}
