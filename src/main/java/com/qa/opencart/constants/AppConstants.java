package com.qa.opencart.constants;

import java.util.Arrays;
import java.util.List;

public class AppConstants {

	public static final String LOGIN_PAGE_TITLE = "Account Login";

	public static final String ACCOUNT_PAGE_TITLE = "My Account";

	public static final String LOGIN_PAGE_URL_FRACTION = "route=account/login";

	public static final String ACC_PAGE_URL_FRACTION = "route=account/account";

	public static final int SHORT_DEFAULT_WAIT = 5;

	public static final int MEDIUM_DEFAULT_WAIT = 10;

	public static final int LONG_DEFAULT_WAIT = 20;

	public static final int POLLING_DEFAULT_WAIT = 2;

	
	public static final int ACCOUNTS_PAGE_HEADERS_COUNT = 4;

	public static final List<String> ACCOUNTS_PAGE_HEADERS_LIST = Arrays.asList("My Account", "My Orders",
			"My Affiliate Account", "Newsletter");

	public static final String USER_REGISTRATION_SUCCESS_MESSAGE = "Your Account Has Been Created!";

	public static final String REGISTER_DATA_SHEET_NAME = "register";
	public static final String PRODUCT_DATA_SHEET_NAME = "product";
	
	
	public static final List<String> FOOTER_PAGE_INFORMATION_LINK = Arrays.asList("About Us", "Delivery Information",
			"Privacy Policy", "Terms & Conditions");
	public static final List<String> FOOTER_PAGE_CUSTOMER_SERVICE_LINK = Arrays.asList("Contact Us", "Returns",
			"Site Map");
	
	public static final String LOGIN_ERROR_MESSAGE= "Warning: No match for E-Mail Address and/or Password.";
			

}
