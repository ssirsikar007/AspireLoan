package com.aspire.loanbe;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

import com.aspire.loanbe.controller.UserControllerTest;
import com.aspire.loanbe.service.impl.UserServiceImplTest;

@RunWith(JUnitPlatform.class)
@SelectClasses({ UserControllerTest.class, UserServiceImplTest.class })
@TestInstance(Lifecycle.PER_CLASS)
public class UserTestSuit {
	// This class doesn't contain any code.
	// It only serves as a holder for the test suite.
}