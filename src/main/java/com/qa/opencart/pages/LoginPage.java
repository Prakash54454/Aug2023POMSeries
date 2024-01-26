package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	// By locators: OR
	private By userName = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By logo = By.cssSelector("img[title='naveenopencart']");

	private By registerLink = By.linkText("Register");
	private By informationServiceLink = By.xpath("//h5[normalize-space()='Information']");
	private By customerServiceLink = By.xpath("//h5[normalize-space()='Customer Service']");

	private By errorMessage = By.cssSelector(".alert.alert-danger.alert-dismissible");

	// page const...
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);

	}

	// page actions/methods:
	@Step("getting login page title")
	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, AppConstants.SHORT_DEFAULT_WAIT);
		System.out.println("login page title:" + title);
		return title;
	}

	@Step("getting login page url")
	public String getLoginPageURL() {
		String url = eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION, AppConstants.SHORT_DEFAULT_WAIT);
		System.out.println("login page url:" + url);
		return url;
	}

	@Step("checking forgot pwd link exist")
	public boolean isForgotPwdLinkExist() {
		return eleUtil.waitForVisibilityOfElement(forgotPwdLink, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
	}

	@Step("checking logo exist")
	public boolean isLogoExist() {
		return eleUtil.waitForVisibilityOfElement(logo, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();

	}

	@Step("username is : {0} and password {1} ")
	public AccountsPage doLogin(String username, String pwd) {
		System.out.println("creds are: " + username + " : " + pwd);
		eleUtil.waitForVisibilityOfElement(userName, AppConstants.MEDIUM_DEFAULT_WAIT).sendKeys(username);
		eleUtil.dosendKeys(password, pwd);
		eleUtil.doClick(loginBtn);

		return new AccountsPage(driver);
	}

	@Step("navigating to register page")
	public RegisterPage navigateToRegisterPage() {
		eleUtil.waitForVisibilityOfElement(registerLink, AppConstants.MEDIUM_DEFAULT_WAIT).click();
		return new RegisterPage(driver);
	}

	public boolean isInformationFooterExist() {

		return eleUtil.waitForVisibilityOfElement(informationServiceLink, AppConstants.MEDIUM_DEFAULT_WAIT)
				.isDisplayed();

	}

	public boolean iscustomerServiceHeaderExist() {

		return eleUtil.waitForVisibilityOfElement(customerServiceLink, AppConstants.MEDIUM_DEFAULT_WAIT).isDisplayed();
	}

	public boolean doLoginwithNegativeCredentials(String username, String pwd) {
		System.out.println("creds are: " + username + " : " + password);
		eleUtil.waitForVisibilityOfElement(userName, AppConstants.MEDIUM_DEFAULT_WAIT).clear();
		eleUtil.waitForVisibilityOfElement(userName, AppConstants.MEDIUM_DEFAULT_WAIT).sendKeys(username);
		eleUtil.waitForVisibilityOfElement(password, AppConstants.MEDIUM_DEFAULT_WAIT).clear();
		eleUtil.dosendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		String errorMessageText = eleUtil.doElementGetText(errorMessage);

		if (errorMessageText.contains(AppConstants.LOGIN_ERROR_MESSAGE)) {
			return true;
		} else {
			return false;
		}

	}

}
