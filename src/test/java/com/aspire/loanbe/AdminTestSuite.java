package com.aspire.loanbe;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

import com.aspire.loanbe.controller.AdminControllerTest;

@RunWith(JUnitPlatform.class)
@SelectClasses({ AdminControllerTest.class })
@TestInstance(Lifecycle.PER_CLASS)
public class AdminTestSuit {

}
