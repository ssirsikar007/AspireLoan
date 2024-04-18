package com.aspire.loanbe.controller;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.aspire.loanbe.dto.ApproveRequest;
import com.aspire.loanbe.service.ILoanService;

@WebMvcTest(AdminController.class)
class AdminControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ILoanService loanService;

	@Test
	void testApproveLoan_Success() throws Exception {
		doNothing().when(loanService).aproveLoan(anyLong(), anyLong());

		ApproveRequest approveRequest = new ApproveRequest();
		approveRequest.setLoanId(123);

		mockMvc.perform(
				post("/api/v1/admin/{id}/approve", 456L).contentType(MediaType.APPLICATION_JSON).content("{\"loanId\": 123}"))
				.andExpect(status().isOk());
	}
}
