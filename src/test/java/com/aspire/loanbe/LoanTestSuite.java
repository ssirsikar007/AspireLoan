package com.aspire.loanbe;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

import com.aspire.loanbe.controller.LoanControllerTest;
import com.aspire.loanbe.repository.LoanRepositoryTest;
import com.aspire.loanbe.repository.UserRepositoryTest;
import com.aspire.loanbe.service.impl.LoanServiceImplTest;
import com.aspire.loanbe.service.impl.UserServiceImplTest;

@RunWith(JUnitPlatform.class)
@SelectClasses({ LoanControllerTest.class, LoanServiceImplTest.class, LoanRepositoryTest.class })
@TestInstance(Lifecycle.PER_CLASS)
public class LoanTestSuit {

}
