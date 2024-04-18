package com.aspire.loanbe.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.aspire.loanbe.dto.LoanRequest;
import com.aspire.loanbe.dto.UserLoanResponse;
import com.aspire.loanbe.exception.CustomerNotFoundException;
import com.aspire.loanbe.exception.InsufficientRepaymentAmountException;
import com.aspire.loanbe.exception.InvalidLoanException;
import com.aspire.loanbe.exception.LoanCompletedException;
import com.aspire.loanbe.exception.LoanNotApprovedException;
import com.aspire.loanbe.exception.LoanNotBelongToUserException;
import com.aspire.loanbe.exception.NotAdminException;
import com.aspire.loanbe.message.ErrorMessageConstants;
import com.aspire.loanbe.message.SuccessMessageConstants;
import com.aspire.loanbe.model.Loan;
import com.aspire.loanbe.model.Member;
import com.aspire.loanbe.model.ScheduledRepayment;
import com.aspire.loanbe.model.StatusEnum;
import com.aspire.loanbe.repository.LoanRepository;
import com.aspire.loanbe.repository.ScheduledRepaymentRepository;
import com.aspire.loanbe.service.IUserService;
import com.aspire.loanbe.util.LoanUtil;

class LoanServiceImplTest {

	@Mock
	private LoanRepository loanRepository;

	@Mock
	private ScheduledRepaymentRepository scheduledRepaymentRepository;

	@Mock
	private IUserService userService;

	@InjectMocks
	private LoanServiceImpl loanService;

	@Mock
	private LoanUtil loanUtil;

	LoanRequest loanRequest;
	Member customer;
	Member admin;
	long loanId = 456L;
	double amount = 1000.0;
	long userId = 789L;
	Loan loan;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
		loanRequest = new LoanRequest(10000.0, 12, new Date());
		customer = new Member();
		admin = new Member("admin", true);
		loan = new Loan();
		loan.setId(1L);
		loan.setState(StatusEnum.APPROVED);
		loan.setCustomer(new Member());
		loan.getCustomer().setId(1L);
	}

	@Test
	void testCreateLoan_Successful() {
		customer.setId(1L);
		when(userService.getCustomerById(1L)).thenReturn(Optional.of(customer));
		when(loanRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));
		loanService.createLoan(loanRequest, 1L);
		verify(userService).getCustomerById(1L);
		verify(loanRepository).save(any());
		verify(scheduledRepaymentRepository, times(12)).save(any());
	}

	@Test
	void testCreateLoan_CustomerNotFound() {
		when(userService.getCustomerById(1L)).thenReturn(Optional.empty());
		assertThrows(CustomerNotFoundException.class, () -> loanService.createLoan(loanRequest, 1L));
		verify(userService).getCustomerById(1L);
		verify(loanRepository, never()).save(any());
		verify(scheduledRepaymentRepository, never()).save(any());
	}

	@Test
	void testGetLoans_Successful() {
		List<Loan> loans = new ArrayList<>();
		loans.add(new Loan());
		when(userService.isValidMember(1L)).thenReturn(true);
		when(loanRepository.findByCustomerId(1L)).thenReturn(loans);
		when(loanUtil.mapToUserLoanResponse(loans)).thenReturn(new ArrayList<UserLoanResponse>());
		List<UserLoanResponse> userLoans = loanService.getLoans(1L);
		assertNotNull(userLoans);
		assertEquals(1, userLoans.size());
	}

	@Test
	public void testApproveLoan_Success() throws CustomerNotFoundException, NotAdminException {
		long loanId = 1L;
		long adminId = 2L;
		Loan loan = new Loan();
		loan.setState(StatusEnum.PENDING);

		when(userService.getCustomerById(adminId)).thenReturn(Optional.of(admin));
		when(loanRepository.findById(loanId)).thenReturn(Optional.of(loan));
		when(loanRepository.save(any(Loan.class))).thenReturn(loan);
		loanService.aproveLoan(loanId, adminId);
		assertEquals(StatusEnum.APPROVED, loan.getState());
		verify(loanRepository, times(1)).save(loan);
	}

	@Test
    void testApproveLoan_NotAdminException() {
      
        when(userService.getCustomerById(1L)).thenReturn(Optional.empty());

    
        assertThrows(NotAdminException.class, () -> loanService.aproveLoan(1L, 1L));
    }

	@Test
	void testApproveLoan_NotAdmin() {
		Member admin2 = new Member();
		admin2.setId(1L);
		Optional<Member> optionalAdmin = Optional.of(admin2);
		Optional<Loan> optionalLoan = Optional.of(new Loan());
		when(userService.getCustomerById(1L)).thenReturn(optionalAdmin);
		when(loanRepository.findById(1L)).thenReturn(optionalLoan);
		when(userService.getCustomerById(1L)).thenReturn(optionalAdmin);
		assertThrows(NotAdminException.class, () -> loanService.aproveLoan(1L, 1L));
	}

	@Test
	void testRepay_Successful() {
		ScheduledRepayment repayment = new ScheduledRepayment();
		repayment.setLoan(loan);
		repayment.setState(StatusEnum.PENDING);
		repayment.setAmount(100.0);
		List<ScheduledRepayment> repayments = new ArrayList<>();
		repayments.add(repayment);

		when(userService.isValidMember(1L)).thenReturn(true);
		when(loanRepository.findById(1L)).thenReturn(Optional.of(loan));
		when(scheduledRepaymentRepository.getByLoanId(1L)).thenReturn(repayments);
		when(scheduledRepaymentRepository.save(any(ScheduledRepayment.class)))
				.thenAnswer(invocation -> invocation.getArgument(0));

		loanService.repay(1L, 100.0, 1L);

		verify(userService).isValidMember(1L);
		verify(loanRepository).findById(1L);
		verify(scheduledRepaymentRepository).getByLoanId(1L);
		verify(scheduledRepaymentRepository).save(any(ScheduledRepayment.class));
	}

	@Test
	void testRepay_InsufficientRepaymentAmount() {

		ScheduledRepayment repayment = new ScheduledRepayment();
		repayment.setLoan(loan);
		repayment.setState(StatusEnum.PENDING);
		repayment.setAmount(100.0);
		List<ScheduledRepayment> repayments = new ArrayList<>();
		repayments.add(repayment);

		when(userService.isValidMember(1L)).thenReturn(true);
		when(loanRepository.findById(1L)).thenReturn(Optional.of(loan));
		when(scheduledRepaymentRepository.getByLoanId(1L)).thenReturn(repayments);
		assertThrows(InsufficientRepaymentAmountException.class, () -> loanService.repay(1L, 50.0, 1L));
	}

	@Test
	void testRepay_InvalidLoan() {
	    when(userService.isValidMember(1L)).thenReturn(true);
	    when(loanRepository.findById(1L)).thenReturn(Optional.empty());

	    assertThrows(InvalidLoanException.class, () -> loanService.repay(1L, 100.0, 1L));
	}

	@Test
	void testRepay_LoanNotBelongToUser() {
		Loan loan = new Loan();
		loan.setId(1L);
		loan.setCustomer(new Member());
		loan.getCustomer().setId(2L);
		loan.setState(StatusEnum.APPROVED);
		when(userService.isValidMember(1L)).thenReturn(true);
		when(loanRepository.findById(1L)).thenReturn(Optional.of(loan));

		assertThrows(LoanNotBelongToUserException.class, () -> loanService.repay(1L, 100.0, 1L));
	}

	@Test
	void testRepay_CompletedLoan() {
		Loan loan = new Loan();
		loan.setId(1L);
		loan.setState(StatusEnum.COMPLETED);
		loan.setCustomer(new Member());
		loan.getCustomer().setId(1L);

		when(userService.isValidMember(1L)).thenReturn(true);
		when(loanRepository.findById(1L)).thenReturn(Optional.of(loan));

		// When & Then
		assertThrows(LoanCompletedException.class, () -> loanService.repay(1L, 100.0, 1L));
	}

	@Test
	public void testGetLoans_InvalidMemberId() {
		when(userService.isValidMember(userId)).thenReturn(false);

		assertThrows(CustomerNotFoundException.class, () -> {
			loanService.getLoans(userId);
		}, ErrorMessageConstants.INVALID_CUSTOMER);
	}

	@Test
	public void testRepay_InvalidCustomerId() {
		when(userService.isValidMember(userId)).thenReturn(false);

		assertThrows(CustomerNotFoundException.class, () -> {
			loanService.repay(loanId, amount, userId);
		}, ErrorMessageConstants.INVALID_CUSTOMER);
	}

	@Test
	public void testRepay_LoanNotApproved() {
		Loan loan = new Loan();
		loan.setState(StatusEnum.PENDING);
		when(userService.isValidMember(userId)).thenReturn(true);
		when(loanRepository.findById(loanId)).thenReturn(Optional.of(loan));

		assertThrows(LoanNotApprovedException.class, () -> {
			loanService.repay(loanId, amount, userId);
		}, SuccessMessageConstants.LOAN_NOT_APPROVED);
	}

}
