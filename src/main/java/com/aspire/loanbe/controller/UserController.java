package com.aspire.loanbe.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aspire.loanbe.dto.UserRequest;
import com.aspire.loanbe.service.IUserService;

import jakarta.validation.Valid;

/**
 * Controller for all user related calls
 */
@RestController
@RequestMapping("/api/v1")
public class UserController {
	private final IUserService userService;

	public UserController(IUserService userService) {
		this.userService = userService;
	}

	@PostMapping("/user")
	public ResponseEntity<?> createUser(@Valid @RequestBody UserRequest userRequest) {
		try {
			String status = userService.createUser(userRequest);
			return new ResponseEntity<>(status, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("Failed to create user: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
