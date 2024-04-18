package com.aspire.loanbe.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AdminTest {
	@Test
	public void testConstructorAndGetters() {

		String username = "Admin";
		boolean isAdmin = true;

		Admin admin = new Admin(username, isAdmin);

		assertEquals(username, admin.getUsername());
		assertEquals(isAdmin, admin.isAdmin());
	}
}
