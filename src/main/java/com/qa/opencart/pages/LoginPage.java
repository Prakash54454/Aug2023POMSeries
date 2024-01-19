package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	// By locators

	private By userName = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By logo = By.cssSelector("img[title='naveenopencart']");
	private By registerLink = By.linkText("Register");

	public LoginPage(WebDriver driver) {

		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}

	// page Action Methods

	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, AppConstants.SHORT_DEFAULT_WAIT);
		System.out.println("Login page title is " + title);

		return title;
	}

	public String getLoginPageURL() {
		String url = eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION, AppConstants.SHORT_DEFAULT_WAIT);
		System.out.println("Page url is " + url);
		return url;

	}
	
	public boolean isForgotPwdLinkExist()
	{
		return eleUtil.waitForVisibilityOfElement(forgotPwdLink, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
	}
	
	public boolean isLogoExist()
	{
		return eleUtil.waitForVisibilityOfElement(logo, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
	}
	
	
	public AccountsPage doLogin(String username, String pwd)
	{
		eleUtil.waitForVisibilityOfElement(userName, AppConstants.SHORT_DEFAULT_WAIT).sendKeys(username);
		eleUtil.dosendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		
		return new AccountsPage(driver);
	}

	
	public RegisterPage navigateToRegisterPage()
	{
		eleUtil.waitForVisibilityOfElement(registerLink, AppConstants.SHORT_DEFAULT_WAIT).click();
		
		return new RegisterPage(driver);
	}
}
