package com.aspire.loanbe.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.aspire.loanbe.model.StatusEnum;

public class LoanResponseTest {

	@Test
	public void testGettersAndSetters() {

		List<ScheduledPaymentResponse> scheduledRepayments = new ArrayList<>();

		Date date = new Date();

		LoanResponse loanResponse = new LoanResponse();

		loanResponse.setId(1L);
		loanResponse.setAmount(1000.0);
		loanResponse.setTerm(12);
		loanResponse.setState(StatusEnum.PENDING);
		loanResponse.setLoanDate(date);
		loanResponse.setScheduledRepayments(scheduledRepayments);

		assertEquals(1L, loanResponse.getId());
		assertEquals(1000.0, loanResponse.getAmount());
		assertEquals(12, loanResponse.getTerm());
		assertEquals(StatusEnum.PENDING, loanResponse.getState());
		assertEquals(date, loanResponse.getLoanDate());
		assertEquals(scheduledRepayments, loanResponse.getScheduledRepayments());
	}

	@Test
	public void testParameterizedConstructor() {

		List<ScheduledPaymentResponse> scheduledRepayments = new ArrayList<>();

		Date date = new Date();

		LoanResponse loanResponse = new LoanResponse(1L, 1000.0, 12, StatusEnum.PENDING, date, scheduledRepayments);

		assertEquals(1L, loanResponse.getId());
		assertEquals(1000.0, loanResponse.getAmount());
		assertEquals(12, loanResponse.getTerm());
		assertEquals(StatusEnum.PENDING, loanResponse.getState());
		assertEquals(date, loanResponse.getLoanDate());
		assertEquals(scheduledRepayments, loanResponse.getScheduledRepayments());
	}
}
