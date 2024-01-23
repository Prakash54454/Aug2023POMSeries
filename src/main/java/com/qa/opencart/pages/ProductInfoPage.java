package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;

	private ElementUtil eleUtil;

	public ProductInfoPage(WebDriver driver) {

		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}

	private By proudctHeader = By.cssSelector("div#content h1");
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	
	private By informationFooterLinks = By.xpath("//div[@class='col-sm-3']/h5[contains(.,'Information')]/following-sibling::ul/li");

	// private Map<String, String> productMap = new HashMap<String, String>();
	// private Map<String, String> productMap = new LinkedHashMap<String, String>();
	private Map<String, String> productMap = new TreeMap<String, String>();

	public String getProductHeaderName() {
		String productHeaderValue = eleUtil.doElementGetText(proudctHeader);
		System.out.println("Product header is " + productHeaderValue);
		return productHeaderValue;

	}

	public int getProductImageCount() {
		int imagesCount = eleUtil.waitForVisibilityOfElements(productImages, AppConstants.MEDIUM_DEFAULT_WAIT).size();

		System.out.println("Product" + getProductHeaderName() + "Images count is" + imagesCount);
		return imagesCount;
	}

//	Brand: Apple
//	Product Code: Product 18
//	Reward Points: 800
//	Availability: In Stock

	private void getProductMetadata() {
		List<WebElement> metaDataList = eleUtil.waitForVisibilityOfElements(productMetaData,
				AppConstants.MEDIUM_DEFAULT_WAIT);

		for (WebElement e : metaDataList) {
			String metaData = e.getText();
			String metakey = metaData.split(":")[0].trim();
			String metavalue = metaData.split(":")[1].trim();
			productMap.put(metakey, metavalue);
		}

	}

	private void getProductPriceData() {

		List<WebElement> metaPriceList = eleUtil.waitForVisibilityOfElements(productPriceData,
				AppConstants.MEDIUM_DEFAULT_WAIT);

		String productPrice = metaPriceList.get(0).getText();
		String productExTaxPrice = metaPriceList.get(1).getText().split(":")[1].trim();

		productMap.put("price", productPrice);
		productMap.put("productExTaxPrice", productExTaxPrice);

	}

	public Map<String, String> getProductDetails() {
		productMap.put("productname", getProductHeaderName());
		getProductMetadata();
		getProductPriceData();
		System.out.println(productMap);
		return productMap;
	}

	
	
}
