package com.cba.coivdgame.Web_Regression.Library;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.cba.coivdgame.API_Regression.Library.GenericMethods;

/**
 * The Class Implementation.
 */
public class Implementation 
{	
	/** Global Variables */
	/** The element. */
	static WebElement element;
	
	/**
	 * Method - Click object.
	 *
	 * @param driver the driver
	 * @param element the element
	 * @return the web element
	 */
	public static WebElement ClickObject(WebDriver driver, WebElement element)
	{
	 try
	 { 
		 System.out.println("Element Name : "+element.getText());
		 Reusable.ElementDisplayed(driver,element);
		 Reusable.ElementToBeClickable(driver,element);
		 Reusable.Click(element);
	 }
	 catch (Exception e)
	 {
		 e.printStackTrace();
	 }
		return element;
	 }
	
	/**
	 * Method - Add Text
	 *
	 * @param driver the driver
	 * @param element the element
	 * @param Text the text
	 * @return the web element
	 */
	public static WebElement AddText(WebDriver driver, WebElement element, String Text)
	{
	 try
	 { 
		 Reusable.ElementDisplayed(driver,element);
		 Reusable.IsEnabled(element);
		 element.clear();
		 Reusable.Sendkeys(element,Text);
	 }
	 catch (Exception e)
	 {
		 e.printStackTrace();
		 
	 }
		return element;
	 }
	
	/**
	 * Method - Get Text
	 *
	 * @param driver the driver
	 * @param element the element
	 * @return the string
	 */
	public static String GetText(WebDriver driver, WebElement element)
	{
	 String Text="No Value";
	 try
	 { 
		 Reusable.ElementDisplayed(driver,element);
		 Text = Reusable.GetText(element);
	 }
	 catch (Exception e)
	 {
		 e.printStackTrace();
	 }
		return Text;		
	 }
	
	/**
	 * Method - VerifyContainsText
	 *
	 * @param driver the driver
	 * @param element the element
	 * @param Text the text
	 */
	public static void VerifyContainsText(WebDriver driver, WebElement element, String Text)
	{
		try
		{ 
		 Reusable.ElementDisplayed(driver,element);
		 Reusable.VerifyContainsText(element, Text);
		}
		catch (Exception e)
		{
			 e.printStackTrace();
		}
	}
	
	/**
	 * Method - Format URL.
	 *
	 * @param myURL the my URL
	 */
	public static void FormatURL(String myURL)
	{
		try
		{ 
			GenericMethods.FormatURL(myURL);
		}
		catch (Exception e)
		{
			 e.printStackTrace();			
		}			
	}
	
}
