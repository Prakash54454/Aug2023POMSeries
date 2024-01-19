package com.qa.opencart.tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountsPageTest extends BaseTest {
	
	
	@BeforeClass
	public void accSetUp()
	{
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void accPageTitleTest()
	{
		AssertJUnit.assertEquals(accPage.getAccPageTitle(), AppConstants.ACCOUNT_PAGE_TITLE);
	}
	
	@Test
	public void accPageURLTest()
	{
		AssertJUnit.assertTrue(accPage.getAccPageURL().contains(AppConstants.ACC_PAGE_URL_FRACTION));
	}
	
	@Test
	public void isLogoutLinkExist()
	{
		AssertJUnit.assertTrue(accPage.isLogoutLinkExist());
	}
	
	@Test
	public void isSearchFieldExist()
	{
		AssertJUnit.assertTrue(accPage.isSearchFieldExist());
	}
	

	@Test
	public void accPageHeadersCountTest()
	{
		List<String> actAccPageHeadersList = accPage.getAccountHeader();
		System.out.println(actAccPageHeadersList);
		AssertJUnit.assertEquals(actAccPageHeadersList,  AppConstants.ACCOUNT_PAGE_HEADER_LIST);
	}
	
	
	@Test
	public void searchTest()
	{
		searchResultsPage = accPage.doSearch("MacBook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		String actProductHeader= productInfoPage.getProductHeaderName();
		AssertJUnit.assertEquals(actProductHeader, "MacBook Pro");
	}
	
}
