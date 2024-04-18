package com.aspire.loanbe.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;

import com.aspire.loanbe.model.StatusEnum;

public class ScheduledPaymentResponseTest {

	@Test
	public void testGettersAndSetters() {

		Date date = new Date();

		ScheduledPaymentResponse scheduledPaymentResponse = new ScheduledPaymentResponse();

		scheduledPaymentResponse.setId(1L);
		scheduledPaymentResponse.setRepaymentDate(date);
		scheduledPaymentResponse.setAmount(1000.0);
		scheduledPaymentResponse.setState(StatusEnum.PENDING);

		assertEquals(1L, scheduledPaymentResponse.getId());
		assertEquals(date, scheduledPaymentResponse.getRepaymentDate());
		assertEquals(1000.0, scheduledPaymentResponse.getAmount());
		assertEquals(StatusEnum.PENDING, scheduledPaymentResponse.getState());
	}
}
