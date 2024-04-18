package com.aspire.loanbe.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.aspire.loanbe.dto.UserRequest;
import com.aspire.loanbe.message.SuccessMessageConstants;
import com.aspire.loanbe.service.IUserService;

@WebMvcTest(UserController.class)
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private IUserService userService;

	@Test
    void testCreateUser_Success() throws Exception {
        // Mock the IUserService
        when(userService.createUser(any(UserRequest.class))).thenReturn(SuccessMessageConstants.SUCCESS);

        // Perform POST request to /user endpoint
        mockMvc.perform(post("/api/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"testUser\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().string(SuccessMessageConstants.SUCCESS));
    }

	@Test
    void testCreateUser_Exception() throws Exception {
        // Mock the IUserService to throw an exception
        when(userService.createUser(any(UserRequest.class))).thenThrow(new RuntimeException("Some error"));

        // Perform POST request to /user endpoint
        mockMvc.perform(post("/api/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"testUser\"}"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string(containsString("Failed to create user: Some error")));
    }
}
