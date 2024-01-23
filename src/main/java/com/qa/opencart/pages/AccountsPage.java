package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {

	WebDriver driver;
	ElementUtil eleUtil;

	private By logoutLink = By.linkText("Logout");
	private By search = By.name("search");
	private By searchIcon = By.cssSelector("div#search button");
	private By accHeaders = By.cssSelector("div#content > h2");
	private By informationFooterLinks = By
			.xpath("//div[@class='col-sm-3']/h5[contains(.,'Information')]/following-sibling::ul/li");

	private By customerServiceFooterLinks = By
			.xpath("//div[@class='col-sm-3']/h5[contains(.,'Customer Service')]/following-sibling::ul/li");

	public AccountsPage(WebDriver driver) {

		this.driver = driver;
		eleUtil = new ElementUtil(driver);

	}

	public String getAccPageTitle() {
		String title = eleUtil.waitForTitleIs(AppConstants.ACCOUNT_PAGE_TITLE, AppConstants.SHORT_DEFAULT_WAIT);

		System.out.println("Account page title " + title);
		return title;
	}

	public String getAccPageURL() {
		String url = eleUtil.waitForURLContains(AppConstants.ACC_PAGE_URL_FRACTION, AppConstants.SHORT_DEFAULT_WAIT);
		System.out.println("acc page url:" + url);
		return url;
	}

	public boolean isLogoutLinkExist() {
		return eleUtil.waitForVisibilityOfElement(logoutLink, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
	}

	public void logout() {
		if (isLogoutLinkExist()) {
			eleUtil.doActionsClick(logoutLink);
		}
	}

	public boolean isSearchFieldExist() {
		return eleUtil.waitForVisibilityOfElement(search, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
	}

	public List<String> getAccountHeader() {
		List<WebElement> headersList = eleUtil.waitForVisibilityOfElements(accHeaders,
				AppConstants.MEDIUM_DEFAULT_WAIT);
		List<String> headersValList = new ArrayList<String>();
		for (WebElement e : headersList) {
			String text = e.getText();
			headersValList.add(text);

		}

		return headersValList;
	}

	public SearchResultsPage doSearch(String searchKey) {
		eleUtil.waitForVisibilityOfElement(search, AppConstants.MEDIUM_DEFAULT_WAIT).clear();
		eleUtil.waitForVisibilityOfElement(search, AppConstants.MEDIUM_DEFAULT_WAIT).sendKeys(searchKey);
		eleUtil.doClick(searchIcon);
		return new SearchResultsPage(driver);
	}

	public List<String> getInformationFooterLink() {
		List<WebElement> informationFooterList = eleUtil.waitForVisibilityOfElements(informationFooterLinks,
				AppConstants.MEDIUM_DEFAULT_WAIT);
		List<String> informationfooterValList = new ArrayList<String>();
		for (WebElement e : informationFooterList) {
			String text = e.getText();
			informationfooterValList.add(text);

		}

		return informationfooterValList;
	}

	public List<String> getCustomerServiceFooterLink() {
		List<WebElement> CustomerServiceFooterList = eleUtil.waitForVisibilityOfElements(customerServiceFooterLinks,
				AppConstants.MEDIUM_DEFAULT_WAIT);
		List<String> CustomerServiceValList = new ArrayList<String>();
		for (WebElement e : CustomerServiceFooterList) {
			String text = e.getText();
			CustomerServiceValList.add(text);

		}

		return CustomerServiceValList;
	}

}
