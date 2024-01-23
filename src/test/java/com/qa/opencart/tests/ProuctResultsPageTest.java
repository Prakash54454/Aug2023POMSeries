package com.qa.opencart.tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class ProuctResultsPageTest extends BaseTest {

	@BeforeClass
	public void productInfoSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@DataProvider
	public Object[][] getSearchData() {
		return new Object[][] { { "MacBook", "MacBook Pro", 4 }, { "MacBook", "MacBook Air", 4 }, { "iMac", "iMac", 3 },
				{ "Samsung", "Samsung SyncMaster 941BW", 1 }

		};
	}

	@DataProvider
	public Object[][] getSearchExcelTestData() {
		return ExcelUtil.getTestData(AppConstants.PRODUCT_DATA_SHEET_NAME);
	}

	@Test(dataProvider = "getSearchData")
	public void productImagesTest(String searchKey, String productName, int imageCount) {
		searchResultsPage = accPage.doSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(productName);
		softAssert.assertEquals(productInfoPage.getProductImageCount(), imageCount);
	}

	@DataProvider
	public Object[][] getprodutData() {
		return new Object[][] {
				{ "MacBook", "MacBook Pro", "Apple", "In Stock", "Product 18", "800", "$2,000.00", "$2,000.00" }

		};
	}

	@Test(dataProvider = "getprodutData")
	public void productInfoTest(String searchKey, String productName, String brandName, String availibility,
			String productCode, String rewardPoint, String price, String extaxprice) {

		System.out.println("Extaxprice " + extaxprice);
		searchResultsPage = accPage.doSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(productName);

		Map<String, String> productDetailsMap = productInfoPage.getProductDetails();

		for (Entry<String, String> e : productDetailsMap.entrySet())
			System.out.println("Key: " + e.getKey() + " Value: " + e.getValue());

		softAssert.assertEquals(productDetailsMap.get("Brand"), brandName);
		softAssert.assertEquals(productDetailsMap.get("Availability"), availibility);
		softAssert.assertEquals(productDetailsMap.get("Product Code"), productCode);
		softAssert.assertEquals(productDetailsMap.get("Reward Points"), rewardPoint);

		softAssert.assertEquals(productDetailsMap.get("price"), price);

		softAssert.assertEquals(productDetailsMap.get("productExTaxPrice"), extaxprice);

		softAssert.assertAll();

	}

}
