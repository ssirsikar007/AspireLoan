package com.aspire.loanbe.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class UserRequestTest {

	String userName = "Suraj";

	@Test
	public void testConstructorAndGetters() {

		UserRequest userRequest = new UserRequest(userName, true);

		assertEquals(userName, userRequest.getUsername());
		assertTrue(userRequest.isAdmin());
	}

	@Test
	public void testSetters() {

		UserRequest userRequest = new UserRequest("", false);

		userRequest.setUsername(userName);
		userRequest.setAdmin(true);

		assertEquals(userName, userRequest.getUsername());
		assertTrue(userRequest.isAdmin());
	}
}
