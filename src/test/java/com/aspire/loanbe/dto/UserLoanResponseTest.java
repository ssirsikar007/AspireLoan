package com.aspire.loanbe.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;

import com.aspire.loanbe.model.StatusEnum;

public class UserLoanResponseTest {

	@Test
	public void testConstructorAndGetters() {

		Date loanDate = new Date();
		UserLoanResponse userLoanResponse = new UserLoanResponse(1L, 1000.0, 12, StatusEnum.PENDING, loanDate);

		assertEquals(1L, userLoanResponse.getId());
		assertEquals(1000.0, userLoanResponse.getAmount());
		assertEquals(12, userLoanResponse.getTerm());
		assertEquals(StatusEnum.PENDING, userLoanResponse.getState());
		assertEquals(loanDate, userLoanResponse.getLoanDate());
	}

	@Test
	public void testSetters() {

		UserLoanResponse userLoanResponse = new UserLoanResponse();

		Date loanDate = new Date();
		userLoanResponse.setId(1L);
		userLoanResponse.setAmount(2000.0);
		userLoanResponse.setTerm(24);
		userLoanResponse.setState(StatusEnum.APPROVED);
		userLoanResponse.setLoanDate(loanDate);

		assertEquals(1L, userLoanResponse.getId());
		assertEquals(2000.0, userLoanResponse.getAmount());
		assertEquals(24, userLoanResponse.getTerm());
		assertEquals(StatusEnum.APPROVED, userLoanResponse.getState());
		assertEquals(loanDate, userLoanResponse.getLoanDate());
	}
}
