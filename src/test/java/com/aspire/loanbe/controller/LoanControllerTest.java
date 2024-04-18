package com.aspire.loanbe.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.aspire.loanbe.dto.LoanRequest;
import com.aspire.loanbe.dto.RepaymentRequest;
import com.aspire.loanbe.dto.UserLoanResponse;
import com.aspire.loanbe.exception.LoanNotApprovedException;
import com.aspire.loanbe.exception.LoanNotFoundException;
import com.aspire.loanbe.message.SuccessMessageConstants;
import com.aspire.loanbe.model.Loan;
import com.aspire.loanbe.model.StatusEnum;
import com.aspire.loanbe.service.ILoanService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(LoanController.class)
class LoanControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ILoanService loanService;

	ObjectMapper mapper;
	LoanRequest loanRequest;
	RepaymentRequest repaymentRequest;
	Loan loan;
	String jsonrepaymentrequest = "";

	@BeforeEach
	void setUp() throws JsonProcessingException {
		mapper = new ObjectMapper();
		loanRequest = new LoanRequest(1000, 12, new Date());
		repaymentRequest = new RepaymentRequest();
		repaymentRequest.setLoanId(1);
		repaymentRequest.setAmount(500);
		jsonrepaymentrequest = mapper.writeValueAsString(repaymentRequest);

	}

	@Test
	void testCreateLoan_Success() throws Exception {
		String jsonRequest = mapper.writeValueAsString(loanRequest);

		doNothing().when(loanService).createLoan(any(LoanRequest.class), anyLong());

		mockMvc.perform(
				post("/api/v1/user/{id}/loan", 123L).contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
				.andExpect(status().isCreated()).andExpect(content().string(SuccessMessageConstants.LOAN_CREATED));

		verify(loanService, times(1)).createLoan(any(LoanRequest.class), anyLong());
	}

	@Test
	void testRepay_Success() throws Exception {

		doNothing().when(loanService).repay(anyLong(), anyDouble(), anyLong());

		mockMvc.perform(post("/api/v1/user/{id}/repay", 123L).contentType(MediaType.APPLICATION_JSON)
				.content(jsonrepaymentrequest)).andExpect(status().isOk())
				.andExpect(content().string("Scheduled payment repaid successfully."));

		verify(loanService, times(1)).repay(anyLong(), anyDouble(), anyLong());
	}

	@Test
	void testRepay_LoanNotFoundException() throws Exception {

		doThrow(LoanNotFoundException.class).when(loanService).repay(anyLong(), anyDouble(), anyLong());

		mockMvc.perform(post("/api/v1/user/{id}/repay", 123L).contentType(MediaType.APPLICATION_JSON)
				.content(jsonrepaymentrequest)).andExpect(status().isNotFound());

		verify(loanService, times(1)).repay(anyLong(), anyDouble(), anyLong());
	}

	@Test
	void testRepay_LoanNotApprovedException() throws Exception {

		doThrow(LoanNotApprovedException.class).when(loanService).repay(anyLong(), anyDouble(), anyLong());

		mockMvc.perform(post("/api/v1/user/{id}/repay", 123L).contentType(MediaType.APPLICATION_JSON)
				.content(jsonrepaymentrequest)).andExpect(status().isBadRequest());

		verify(loanService, times(1)).repay(anyLong(), anyDouble(), anyLong());
	}

	@Test
	void testGetLoans_Success() throws Exception {

		UserLoanResponse loanResponse = new UserLoanResponse();
		loanResponse.setId(1L);
		loanResponse.setAmount(1000.0);
		loanResponse.setTerm(12);

		loanResponse.setState(StatusEnum.PENDING);

		when(loanService.getLoans(anyLong())).thenReturn(Collections.singletonList(loanResponse));

		mockMvc.perform(get("/api/v1/user/{userId}/loans", 123L)).andExpect(status().isOk());

		verify(loanService, times(1)).getLoans(anyLong());
	}

}
