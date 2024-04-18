package com.aspire.loanbe.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.aspire.loanbe.dto.UserRequest;
import com.aspire.loanbe.model.Member;
import com.aspire.loanbe.repository.UserRepository;
import com.aspire.loanbe.service.IUserService;

class UserServiceImplTest {

	@Mock
	private UserRepository userRepository;
	private IUserService userService;
	AutoCloseable autoClosable;
	Member member;
	Member memberOne;
	Long userId;

	@BeforeEach
	void setUp() {
		autoClosable = MockitoAnnotations.openMocks(this);
		userService = new UserServiceImpl(userRepository);
		member = new Member("Suraj");
		memberOne = new Member("testUser", false);
		userId = 1L;
	}

	@AfterEach
	void tearDown() throws Exception {
		autoClosable.close();
	}

	@Test
	void testCreateUserSuccessfull() {
		mock(Member.class);
		mock(UserRepository.class);

		when(userRepository.save(any(Member.class))).thenReturn(member);
		assertThat(userService.createUser(new UserRequest("Suraj", false))).isNotNull();
	}

	@Test
	public void testGetCustomerById_ExistingId_Success() {

		when(userRepository.findById(userId)).thenReturn(Optional.of(memberOne));

		Optional<Member> result = userService.getCustomerById(userId);

		assertTrue(result.isPresent());
		assertEquals(memberOne, result.get());
	}

	@Test
	public void testGetCustomerById_NonExistingId_Success() {

		when(userRepository.findById(userId)).thenReturn(Optional.empty());

		Optional<Member> result = userService.getCustomerById(userId);

		assertFalse(result.isPresent());
	}

	@Test
	public void testIsValidMember_ExistingId_Success() {

		when(userRepository.findById(userId)).thenReturn(Optional.of(new Member()));

		boolean isValid = userService.isValidMember(userId);

		assertTrue(isValid);
	}

	@Test
	public void testIsValidMember_NonExistingId_Success() {

		when(userRepository.findById(userId)).thenReturn(Optional.empty());

		boolean isValid = userService.isValidMember(userId);

		assertFalse(isValid);
	}

}
