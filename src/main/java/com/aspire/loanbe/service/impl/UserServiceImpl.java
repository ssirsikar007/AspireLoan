package com.aspire.loanbe.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.aspire.loanbe.dto.UserRequest;
import com.aspire.loanbe.message.SuccessMessageConstants;
import com.aspire.loanbe.model.Member;
import com.aspire.loanbe.repository.UserRepository;
import com.aspire.loanbe.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	private final UserRepository memberRespository;

	public UserServiceImpl(UserRepository memberRespository) {
		this.memberRespository = memberRespository;
	}

	@Override
	public String createUser(UserRequest userRequest) {
		// Create a new User object from the UserRequest data
		Member newUser = new Member(userRequest.getUsername(), userRequest.isAdmin());

		// Save the new user in the database
		memberRespository.save(newUser);
		return SuccessMessageConstants.SUCCESS;
	}

	@Override
	public Optional<Member> getCustomerById(Long userId) {
		return memberRespository.findById(userId);
	}

	@Override
	public boolean isValidMember(Long userId) {
		return memberRespository.findById(userId).isPresent();
	}

}
