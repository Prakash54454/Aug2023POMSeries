package com.qa.opencart.tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.AssertJUnit;

import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.listeners.TestAllureListener;

@Listeners(TestAllureListener.class)
public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accSetUp() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void accPageTitleTest() {
		Assert.assertEquals(accPage.getAccPageTitle(), AppConstants.ACCOUNT_PAGE_TITLE);
	}

	@Test
	public void accPageURLTest() {
		Assert.assertTrue(accPage.getAccPageURL().contains(AppConstants.ACC_PAGE_URL_FRACTION));
	}

	@Test
	public void isLogoutLinkExist() {
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}

	@Test
	public void isSearchFieldExist() {
		Assert.assertTrue(accPage.isSearchFieldExist());
	}

	@Test
	public void accPageHeadersCountTest() {
		List<String> actAccPageHeadersList = accPage.getAccountHeader();
		System.out.println(actAccPageHeadersList);
		Assert.assertEquals(actAccPageHeadersList, AppConstants.ACCOUNTS_PAGE_HEADERS_LIST);
	}

	@Test
	public void accPageHeadersTest() {
		List<String> actAccPageHeadersList = accPage.getAccountHeader();
		System.out.println(actAccPageHeadersList);
		// sort the actual list
		// sort the expected list
		// compare
		Assert.assertEquals(actAccPageHeadersList, AppConstants.ACCOUNTS_PAGE_HEADERS_LIST);
	}

	@Test
	public void accPageHeadersTestSort() {
		List<String> actAccPageHeadersList = accPage.getAccountHeader();
		System.out.println(actAccPageHeadersList);
		Collections.sort(actAccPageHeadersList);
		Collections.sort(AppConstants.ACCOUNTS_PAGE_HEADERS_LIST);
		System.out.println(actAccPageHeadersList);
		System.out.println(AppConstants.ACCOUNTS_PAGE_HEADERS_LIST);
		Assert.assertEquals(actAccPageHeadersList, AppConstants.ACCOUNTS_PAGE_HEADERS_LIST);
	}

	@Test
	public void searchTest() {
		searchResultsPage = accPage.doSearch("MacBook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		String actProductHeader = productInfoPage.getProductHeaderName();
		Assert.assertEquals(actProductHeader, "MacBook Pro");
	}

	@Test
	public void footerinformationLinkTest() {
		List<String> informationFooterList = accPage.getInformationFooterLink();
		System.out.println(informationFooterList);

		System.out.println(AppConstants.FOOTER_PAGE_INFORMATION_LINK);
		Assert.assertEquals(informationFooterList, AppConstants.FOOTER_PAGE_INFORMATION_LINK);
	}

	@Test
	public void footercustomerServiceLinkTest() {
		List<String> customerServiceFooterList = accPage.getCustomerServiceFooterLink();

		System.out.println(customerServiceFooterList);

		System.out.println(AppConstants.FOOTER_PAGE_CUSTOMER_SERVICE_LINK);

		Assert.assertEquals(customerServiceFooterList, AppConstants.FOOTER_PAGE_CUSTOMER_SERVICE_LINK);
	}

}
