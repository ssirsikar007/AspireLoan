package com.aspire.loanbe.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ApproveRequestTest {

	@Test
	public void testConstructorAndGetters() {

		ApproveRequest approveRequest = new ApproveRequest(1L);

		assertEquals(1L, approveRequest.getLoanId());
	}

	@Test
	public void testSetters() {

		ApproveRequest approveRequest = new ApproveRequest();

		approveRequest.setLoanId(2L);

		assertEquals(2L, approveRequest.getLoanId());
	}
}
