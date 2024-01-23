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
	private By informationHeader = By.xpath("//h5[text()='Information']");
	private By customerServiceHeader = By.xpath("//h5[normalize-space()='Customer Service']");
	
	private By loginErrorMessage = By.cssSelector("div.alert.alert-danger.alert-dismissible");

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

	public boolean isForgotPwdLinkExist() {
		return eleUtil.waitForVisibilityOfElement(forgotPwdLink, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
	}

	public boolean isLogoExist() {
		return eleUtil.waitForVisibilityOfElement(logo, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
	}

	public AccountsPage doLogin(String username, String pwd) {
		System.out.println("creds are: " + username + " : " + pwd);
		System.out.println("#####Prakash######");
		eleUtil.waitForVisibilityOfElement(userName, AppConstants.SHORT_DEFAULT_WAIT).sendKeys(username);
		eleUtil.dosendKeys(password, pwd);
		eleUtil.doClick(loginBtn);

		return new AccountsPage(driver);
	}
	
	
	public boolean doLoginwithNegativeCredentials(String username, String pwd) {
		eleUtil.waitForVisibilityOfElement(userName, AppConstants.MEDIUM_DEFAULT_WAIT).clear();
		eleUtil.waitForVisibilityOfElement(userName, AppConstants.SHORT_DEFAULT_WAIT).sendKeys(username);
		eleUtil.waitForVisibilityOfElement(password, AppConstants.MEDIUM_DEFAULT_WAIT).clear();
		eleUtil.dosendKeys(password, pwd);
		eleUtil.doClick(loginBtn);

		eleUtil.waitForVisibilityOfElement(loginErrorMessage, AppConstants.MEDIUM_DEFAULT_WAIT);
		
		String errorMessage = eleUtil.doElementGetText(loginErrorMessage);
		
		System.out.println(errorMessage);
		
		if(errorMessage.contains(AppConstants.LOGIN_ERROR_MESSAGE))
		{
			return true;
		}
		return false;
	}
	

	public RegisterPage navigateToRegisterPage() {
		eleUtil.waitForVisibilityOfElement(registerLink, AppConstants.SHORT_DEFAULT_WAIT).click();

		return new RegisterPage(driver);
	}
	
	public boolean isInformationHeaderExist()
	{
		return eleUtil.waitForVisibilityOfElement(informationHeader, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
	}
	
	public boolean iscustomerServiceHeaderExist()
	{
		return eleUtil.waitForVisibilityOfElement(customerServiceHeader, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
	}
	
}
