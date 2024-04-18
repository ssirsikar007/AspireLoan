package com.aspire.loanbe.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CustomerTest {

	@Test
	public void testConstructorAndGetters() {

		String username = "Customer";
		boolean isAdmin = false;

		Customer customer = new Customer(username, isAdmin);

		assertEquals(username, customer.getUsername());
		assertEquals(isAdmin, customer.isAdmin());
	}
}
