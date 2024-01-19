package com.qa.opencart.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class LoginPageTest extends BaseTest {
	
	
	@Test(priority=1)
	public void loginPageTitleTest()
	{
		String actTitle = loginPage.getLoginPageTitle();
		AssertJUnit.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE);
		
	}
	
	@Test(priority=2)
	public void loginPageURLTest()
	{
		String actURL =loginPage.getLoginPageURL();
		AssertJUnit.assertTrue(actURL.contains(AppConstants.LOGIN_PAGE_URL_FRACTION));
	}
	
	@Test(priority=3)
	public void forgotPwdLinkExistTest()
	{
		AssertJUnit.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	
	@Test(priority=4)
	public void appLogoExistTest()
	{
		AssertJUnit.assertTrue(loginPage.isLogoExist());
	}
	
	@Test(priority=5)
	public void loginTest()
	{
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		AssertJUnit.assertTrue(accPage.isLogoutLinkExist());
	}

}
