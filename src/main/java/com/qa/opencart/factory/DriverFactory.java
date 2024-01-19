package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.qa.opencart.exception.FrameworkException;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;

	public WebDriver initDriver(Properties prop) {
		String browserName = prop.getProperty("browser");

		System.out.println("The browsername is " + browserName);

		optionsManager = new OptionsManager(prop);

		switch (browserName.trim().toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver(optionsManager.getChromeOption());
			break;
		case "firefox":
			driver = new FirefoxDriver(optionsManager.getFirefoxOption());
			break;
		case "edge":
			driver = new EdgeDriver(optionsManager.getEdgeOption());
			break;
		default:
			System.out.println("Please pass right browser");
			throw new FrameworkException("No browser found");

		}

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));

		return driver;

	}

	public Properties initProp() {
		prop = new Properties();

		try {
			FileInputStream ip = new FileInputStream("./src/test/resource/config/config.properties");

			prop.load(ip);

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		return prop;

	}
}
