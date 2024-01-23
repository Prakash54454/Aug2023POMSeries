package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class LoginPageNegativeTest extends BaseTest {

	@DataProvider
	public Object[][] incorrectLoginTestData() {
		return new Object[][] { { "username4", "username@123" }, { "username5", "username@123" },
				{ "username6", "username@123" }

		};
	}

	@Test(dataProvider = "incorrectLoginTestData")
	public void loginTest(String username, String password) {
		
		Assert.assertTrue(loginPage.doLoginwithNegativeCredentials(username, password));
	}

}
