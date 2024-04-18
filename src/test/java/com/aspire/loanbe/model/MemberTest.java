package com.aspire.loanbe.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class MemberTest {

	@Test
	public void testEmptyConstructor() {

		Member member = new Member();

		assertNull(member.getId());
		assertNull(member.getUsername());
		assertFalse(member.isAdmin());
	}

	@Test
	public void testParameterizedConstructor() {

		String username = "testUser";
		boolean isAdmin = true;

		Member member = new Member(username, isAdmin);

		assertNull(member.getId());
		assertEquals(username, member.getUsername());
		assertEquals(isAdmin, member.isAdmin());
	}

	@Test
	public void testParameterizedConstructorTwo() {

		String username = "testUser";

		Member member = new Member(username);

		assertNull(member.getId());
		assertEquals(username, member.getUsername());
		assertEquals(false, member.isAdmin());
	}

	@Test
	public void testSettersAndGetters() {

		Member member = new Member();

		member.setId(1L);
		member.setUsername("testUser");
		member.setAdmin(true);

		assertEquals(1L, member.getId());
		assertEquals("testUser", member.getUsername());
		assertEquals(member.isAdmin(), true);
	}
}
