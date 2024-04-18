package com.aspire.loanbe.service;

import java.util.List;

import com.aspire.loanbe.dto.LoanRequest;
import com.aspire.loanbe.dto.UserLoanResponse;
import com.aspire.loanbe.exception.CustomerNotFoundException;
import com.aspire.loanbe.exception.NotAdminException;

public interface ILoanService {
	public void createLoan(LoanRequest loanRequest, Long customerId);

	public List<UserLoanResponse> getLoans(Long userId);

	public void aproveLoan(long loanId, long adminId) throws CustomerNotFoundException, NotAdminException;

	public void repay(long loanId, double amount, long userId);

}
