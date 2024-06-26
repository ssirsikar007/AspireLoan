package com.aspire.loanbe.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.aspire.loanbe.dto.UserLoanResponse;
import com.aspire.loanbe.model.Loan;
import com.aspire.loanbe.model.ScheduledRepayment;
import com.aspire.loanbe.model.StatusEnum;

public class LoanUtil {
	private LoanUtil() {

	}

	private static LoanUtil instance = null;

	public static LoanUtil getInstance() {
		if (instance == null) {
			synchronized (MemberUtil.class) {
				if (instance == null) {
					instance = new LoanUtil();
				}
			}
		}
		return instance;
	}

	public List<UserLoanResponse> mapToUserLoanResponse(List<Loan> loans) {
		return loans.stream().map(this::mapToUserLoan).collect(Collectors.toList());
	}

	public UserLoanResponse mapToUserLoan(Loan loan) {
		return new UserLoanResponse(loan.getId(), loan.getAmount(), loan.getTerm(), loan.getState(),
				loan.getLoanDate());
	}

	public List<ScheduledRepayment> calculateScheduledRepayments(Loan loan) {
		List<ScheduledRepayment> scheduledPayments = new ArrayList<>();
		double amount = loan.getAmount();
		int term = loan.getTerm();
		Date date = loan.getLoanDate();
		double spliAmounts = amount / term;
		while (term > 0) {
			date = getNextPaymentDate(date);
			ScheduledRepayment scheduledRepayment = new ScheduledRepayment(loan, date, spliAmounts, StatusEnum.PENDING);
			scheduledPayments.add(scheduledRepayment);
			term--;
		}
		return scheduledPayments;
	}

	public static Date getNextPaymentDate(Date date) {
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		LocalDate newLocalDate = localDate.plusDays(7);

		return Date.from(newLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}
}
