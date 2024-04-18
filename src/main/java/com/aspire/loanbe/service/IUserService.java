package com.aspire.loanbe.service;

import java.util.Optional;

import com.aspire.loanbe.dto.UserRequest;
import com.aspire.loanbe.model.Member;

public interface IUserService {
	public String createUser(UserRequest userRequest);

	public Optional<Member> getCustomerById(Long userId);

	public boolean isValidMember(Long userId);

}
