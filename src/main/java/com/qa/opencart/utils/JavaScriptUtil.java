package com.qa.opencart.utils;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {
	
	WebDriver driver;
	JavascriptExecutor js;
	
	public JavaScriptUtil(WebDriver driver)
	{
		this.driver= driver;
		js = (JavascriptExecutor) driver;
		
	}

	public String getTitleByJs()
	{
		return js.executeScript("return document.title").toString();
		
	}
	
	public String getURLByJs()
	{
		return js.executeScript("return document.URL").toString();
		
	}
	
	
	public void genrateAlert(String message)
	{
		js.executeScript("alert('"+message+"')");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.switchTo().alert().accept();
		
	}
	
	public void genrateConfirm(String message) throws InterruptedException
	{
		js.executeScript("confirm('"+message+"')");
		Thread.sleep(5000);
		driver.switchTo().alert().accept();
		
	}
	
	public void generatePrompt(String message, String value)
	{
		js.executeScript("prompt('"+message+"')");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Alert alert = driver.switchTo().alert();
		System.out.println(alert.getText());
		alert.sendKeys(value);
		alert.accept();
		
		
	}
	
	public void goBackWithJS()
	{
		js.executeScript("history.go(-1)");
	}
	
	public void goForwardWithJS()
	{
		js.executeScript("history.go(1)");
	}
	
	public void goRefreshWithJS()
	{
		js.executeScript("history.go(0)");
	}
	
	public String getPageInnerText()
	{
		return js.executeScript("return document.documentElement.innerText").toString();
	}
	
	
	public void JSpageScrollDown()
	{
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	
	public void JSpageScrollDownAtMiddle()
	{
		js.executeScript("window.scrollTo(0, document.body.scrollHeight/2)");
	}
	
	public void JSpageScrollDown(String height)
	{
		js.executeScript("window.scrollTo(0, "+height+")");
	}
	
	public void JSpageScrollUp()
	{
		js.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
	}
	
	public void scrollUptoElement(WebElement el)
	{
		js.executeScript("arguments[0].scrollIntoView(true)", el);
	}
	
	
	public void zoomChromeEdgeSafari(String zoomPercentage) {
		String zoom = "document.body.style.zoom = '"+zoomPercentage+"%'";
		js.executeScript(zoom);
	}
	
	/**
	 * example: "document.body.style.MozTransform = 'scale(0.5)'; ";
	 * @param zoomPercentage
	 */
	public void zoomFirefox(String zoomPercentage) {
		String zoom = "document.body.style.MozTransform = 'scale("+zoomPercentage+")'";
		js.executeScript(zoom);
	}

	public void drawBorder(WebElement el)
	{
		js.executeScript("arguments[0].style.border='3px solid red'", el);
	}
	
	public void flash(WebElement el)
	{
		String bgcolor = el.getCssValue("backgroundColor");
		for(int i =0; i<10; i++)
		{
			changeColor("rgb(0,200,0)", el);// Green
			changeColor(bgcolor, el);// Purple
		}
	}

	public void changeColor(String color, WebElement el) {
		
		js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", el);
		
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
		}
	}
	
	public void clickByJS(WebElement el)
	{
		js.executeScript("arguments[0].click()", el);
	}
	
	public void sendKeysByJS(String id, String value)
	{
		js.executeScript("document.getElementById('" + id + "').value='" + value + "'");
	}
	
}
