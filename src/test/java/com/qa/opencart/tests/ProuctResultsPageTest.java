package com.qa.opencart.tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class ProuctResultsPageTest extends BaseTest{
	
	
	@BeforeClass
	public void productInfoSetup()
	{
		accPage = loginPage.doLogin(prop.getProperty("username"),  prop.getProperty("password"));
	}

	
	@DataProvider
	public Object[][] getSearchData() {
		return new Object[][] {
			{"MacBook", "MacBook Pro", 4},
			{"MacBook", "MacBook Air", 4},
			{"iMac", "iMac", 3},
			{"Samsung", "Samsung SyncMaster 941BW", 1}

		};
	}
	
//	@DataProvider
//	public Object[][] getSearchExcelTestData() {
//		return ExcelUtil.getTestData(AppConstants.PRODUCT_DATA_SHEET_NAME);
//	}
	
	@Test(dataProvider="getSearchData")
	public void productImagesTest(String searchKey, String productName, int imageCount)
	{
		searchResultsPage= accPage.doSearch(searchKey);
		 productInfoPage = searchResultsPage.selectProduct(productName);
		 softAssert.assertEquals(productInfoPage.getProductImageCount(), imageCount);
	}
	
	@Test
	public void productInfoTest()
	{
		searchResultsPage = accPage.doSearch("Macbook");
		productInfoPage = searchResultsPage.selectProduct("Macbook Pro");
		
		Map<String, String> productDetailsMap = productInfoPage.getProductDetails();
		
		softAssert.assertEquals(productDetailsMap.get("Brand"), "Apple");
		softAssert.assertEquals(productDetailsMap.get("Availability"), "In Stock");
		softAssert.assertEquals(productDetailsMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(productDetailsMap.get("Reward Points"), "800");

		softAssert.assertEquals(productDetailsMap.get("price"), "$2,000.00");
		softAssert.assertEquals(productDetailsMap.get("extaxprice"), "$2,000.00");
		
		softAssert.assertAll();
		
	}
	
}

