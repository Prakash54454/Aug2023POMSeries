package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.exception.FrameworkException;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	public static String highlight = null;

	public WebDriver initDriver(Properties prop) {

		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") :prop.getProperty("browser");
		//String browserName = prop.getProperty("browser");
		// String browserName = System.getProperty("browser");
		System.out.println("The browsername is " + browserName);
		highlight = prop.getProperty("highlight");

		optionsManager = new OptionsManager(prop);

		switch (browserName.trim().toLowerCase()) {
		case "chrome":
//			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
//				// run it on grid:
//				initRemoteDriver(browserName);
//			} else {
			// run it on local:
		    tlDriver.set(new ChromeDriver(optionsManager.getChromeOption()));
			//driver =new ChromeDriver(optionsManager.getChromeOption());
			// }
			break;
		case "firefox":
//			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
//				// run it on grid:
//				initRemoteDriver(browserName);
//			} else {
			// run it on local:
			 tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOption()));
			//driver =new FirefoxDriver(optionsManager.getFirefoxOption());
			// }
			break;

		case "edge":
//			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
//				// run it on grid:
//				initRemoteDriver(browserName);
//			} else {
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOption()));
			//driver =new EdgeDriver(optionsManager.getEdgeOption());
			// }
			break;

		case "safari":
			tlDriver.set(new SafariDriver());
			//driver=new SafariDriver(optionsManager.getSafariOption());

			break;
		default:
			System.out.println("Please pass right browser");
			throw new FrameworkException("No browser found");

		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));

		return getDriver();

	}

	/**
	 * run tests on grid
	 * 
	 * @param browserName
	 */
	private void initRemoteDriver(String browserName) {
		System.out.println("Running tests on GRID with browser: " + browserName);

		try {
			switch (browserName.toLowerCase().trim()) {
			case "chrome":
				tlDriver.set(
						new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getChromeOption()));
				break;
			case "firefox":
				tlDriver.set(
						new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getFirefoxOption()));
				break;
			case "edge":
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getEdgeOption()));
				break;

			default:
				System.out.println("wrong browser info..can not run on grid remote machine....");
				break;
			}
		} catch (MalformedURLException e) {

		}

	}

	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	public Properties initProp() {

		// mvn clean install -Denv="qa"
		// mvn clean install
		FileInputStream ip = null;
		prop = new Properties();

		String envName = System.getProperty("env");
		
		//String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") :prop.getProperty("browser");
		System.out.println("env name is: " + envName);

		try {
			if (envName == null) {
				System.out.println("your env is null...hence running tests on QA env...");
				ip = new FileInputStream("./src/test/resources/config/config.qa.properties");
			}

			else {
				switch (envName.toLowerCase().trim()) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/configs/config.qa.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/test/resources/configs/config.dev.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resources/config/config.stage.properties");
					break;
				case "uat":
					ip = new FileInputStream("./src/test/resources/config/config.uat.properties");
					break;
				case "prod":
					ip = new FileInputStream("./src/test/resources/config/config.properties");
					break;

				default:
					System.out.println("please pass the right env name..." + envName);
					throw new FrameworkException("Wrong Env Name: " + envName);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Catched Exception");
			e.printStackTrace();

		}

		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;

	}

	/**
	 * take screenshot
	 */
	public static String getScreenshot(String methodName) {

		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);

		String path = System.getProperty("user.dir") + "/screenshot/" + methodName + "_" + System.currentTimeMillis()
				+ ".png";

		File destination = new File(path);

		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}

}
