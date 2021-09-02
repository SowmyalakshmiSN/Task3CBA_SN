package com.cba.coivdgame.Web_Regression.Library;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;

import com.cba.coivdgame.utils.ConstantsProperties;
import com.google.common.base.Predicate;


/**
 * The Class Reusable.
 */
public class Reusable 
{
	/** Global Variable Declaration */
	/** The env name. */
	/** The filename. */
	/** The filename 1. */
	/** The loader. */
	/** The resource stream. */
	/** The element. */
	/** The props. */
	/** The propval. */
	/** The parent window handle. */
	/** The window handlers. */
	static String env_name;
	static String filename;
	static String filename1;
	static ClassLoader loader; 
	static InputStream resourceStream;
	static WebElement element;	
	private static Properties props = new Properties();
	private static Properties propval = new Properties();
	public static String parentWindowHandle = null; 	
	public static List<String> windowHandlers = new ArrayList<String>();
	
	/** Methods */
	/**
	 * Method - To Verify ElementToBeClickable
	 *
	 * @param driver the driver
	 * @param element the element
	 * @return the web element
	 */
	public static WebElement ElementToBeClickable(WebDriver driver,WebElement element)
	{		
		try
		 {  	
			WebDriverWait wait = new WebDriverWait(driver,60);
			wait.until(ExpectedConditions.elementToBeClickable(element));
		 }
		 catch (Exception e)
		 {
			 e.printStackTrace();
		 }
		return element;
	}
	
	/**
	 *  Method - To Verify Element displayed.
	 *
	 * @param driver the driver
	 * @param element the element
	 * @return the web element
	 */
	public static WebElement ElementDisplayed(WebDriver driver,WebElement element)
	{		
		 try
		 {  
			 WebElement Element = element;
			 Element.isDisplayed();
			 driver.manage().timeouts().pageLoadTimeout(60,TimeUnit.SECONDS);
			 logMessage(element+" Should Display.",element+" Is Displayed","pass");
			return element;
		 }
		 catch (Exception e)
		 {
			 Assert.fail("Element Not Displayed : "+ element);
		 }
		return null;
	}
	
	/**
	 *  Method - To Click.
	 *
	 * @param element the element
	 * @return the web element
	 */
	public static WebElement Click(WebElement element)
	{		
		try
		{  
			WebElement Element = element;
			Element.click();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return element;
	}
	
	/**
	 * Method - To Add Text [Send Keys]
	 *
	 * @param element the element
	 * @param Text the text
	 * @return the web element
	 */
	public static WebElement Sendkeys(WebElement element, String Text)
	{		
		try
		{  
			 WebElement Element = element;
			 Element.clear();
			 Element.sendKeys(Text);
		 }
		 catch (Exception e)
		 {
			 e.printStackTrace();
		 }
		 return element;
	}
	
	/**
	 * Method - To Check Element Enabled
	 *
	 * @param element the element
	 * @return the web element
	 */
	public static WebElement IsEnabled(WebElement element)
	{		
		try
		{  
			 WebElement Element = element;
			 Element.isEnabled();
		}
		catch (Exception e)
		{
			 e.printStackTrace();
		}
		return element;
	}
	
	/**
	 * Method - Switch To New Window.
	 *
	 * @param driver the driver
	 * @return counter - No. of windows
	 * @category switch from parent window to child window
	 */
	public static int switchToNewWindow(WebDriver driver)
	{
		int counter = 0;
		try{
			if(parentWindowHandle == null)
				parentWindowHandle= driver.getWindowHandle();
			System.out.println("Here1"+parentWindowHandle);
			for(String winHandle : driver.getWindowHandles()){
				windowHandlers.add(winHandle);
				System.out.println("Here2"+winHandle);
				if(!parentWindowHandle.contains(winHandle))
					driver.switchTo().window(winHandle);
				counter++;
			}
		waitForPageToLoad(driver);
		if(counter > windowHandlers.size()){
			windowHandlers.add(driver.getWindowHandle());
			System.out.println("handler : "+driver.getWindowHandle().toString());
			System.out.println("Title is :"+driver.getTitle().toString());
		}
		}catch(Exception e){
			logMessage("Switch to new window.","Exception occured. Exception : "+e.getMessage(), "fail");
			e.printStackTrace();
		}
		System.out.println("Counter Size : "+counter);
		return counter;
	}
	
	/**
	 * Method - Switch To Original Window.
	 *
	 * @param driver the driver
	 * @category switch from child window to parent window
	 */
	public static void switchToOriginalWindow(WebDriver driver){
		try{
			System.out.println("Windows Count :"+windowHandlers.size());
			System.out.println("Windows Count Now :"+(windowHandlers.size()-1));
			if(windowHandlers.size() > 0)
				windowHandlers.remove(windowHandlers.size()-1);
			System.out.println("Windows Count After Now :"+(windowHandlers.iterator()));
			
			Iterator<String> itr = windowHandlers.iterator();
			String handler = "";
			while(itr.hasNext())
			{
				handler = itr.next();
			System.out.println("handler : "+handler);
			if(handler != "")
				driver = driver.switchTo().window(handler);
			}
		}catch(Exception e){
			logMessage("Switch to parent window.","Exception occured. Exception : "+e.getMessage(), "fail");
		}
		waitForPageToLoad(driver);
	} 
	
	/**
	 * Method - Wait For Page To Load.
	 *
	 * @param driver the driver
	 * @category Statement will wait to execute until page to load is complete
	 */
	public static void waitForPageToLoad(WebDriver driver)
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, 60);
			Predicate<WebDriver> pageLoaded = new Predicate<WebDriver>()
		    {
		    	 public boolean apply(WebDriver input)
		    	 {
		    		 return ((JavascriptExecutor) input).executeScript("return document.readyState").equals("complete");
		    	 }
		    };
		    wait.until(pageLoaded);
			
		}
		catch(Exception e)
		{
			System.out.println("Exception occured while waiting for loading.Exception : "+e.getMessage());
		}
	}
	
	/**
	 * Method - Wait In Seconds.
	 *
	 * @param time seconds to wait
	 * @category Wait in seconds
	 */
	public static void waitInSeconds(int time)
	{
		try
		{
            Thread.sleep(time * 2000);
		}
		catch (InterruptedException e)
		{
            e.printStackTrace();
            System.out.println("exception in tread.sleep..");
		}
	}
	
	/**
	 * Method - Log Message.
	 *
	 * @param expectedMsg the expected msg
	 * @param actualMsg the actual msg
	 * @param status the status
	 */
	public static void logMessage(String expectedMsg,String actualMsg,String status)
	{
		if(status.equalsIgnoreCase("pass"))
		{
			System.out.println("Status : " + status + " :: "+actualMsg);
			Assert.assertTrue(true);
		}
		else
		{
			System.out.println("Status : " + status + " :: "+actualMsg);
			Assert.assertTrue(false);
		}		
	}
	
	/**
	 * Method - Log.
	 *
	 * @param expectedMsg the expected msg
	 */
	public static void log(String expectedMsg)
	{
		System.out.println(expectedMsg);
	}
	
	/**
	 * Method - SetUpDriver.
	 *
	 * @param env the env
	 * @return the string
	 * @throws InterruptedException the interrupted exception
	 */
	public static String Setupdriver(String env) throws  InterruptedException 
    {		
		//System.out.println("Here in Setupdriver In Reusable::");
		env_name = env;
        if(env_name.equalsIgnoreCase("prod")) filename = ConstantsProperties.ProdPropFile;
	    loader = Thread.currentThread().getContextClassLoader();
	    try
	    {
		   resourceStream = loader.getResourceAsStream(filename);
	       props.load(resourceStream);
	    }
	    catch(Exception e)
	    {
		   System.out.println(e);
	    }	   	
	    return env;
     }	
	
	/**
	 * Method - Client Prop File.
	 *
	 * @throws InterruptedException the interrupted exception
	 */
	public static void ClientPropFile() throws  InterruptedException 
    {
		filename1 = ConstantsProperties.ClientPropFile;
		System.out.println("File Name :"+filename1);
		loader = Thread.currentThread().getContextClassLoader();
	    try
	    {
		   resourceStream = loader.getResourceAsStream(filename1);
		   System.out.println("resourceStream   :"+resourceStream);
		   propval.load(resourceStream);
	    }
	    catch(Exception e)
	    {
		   System.out.println(e);
	    }	  
    }	
	
	/**
	 * Method - Write Into Property File.
	 *
	 * @param Keyvalue1 the keyValue 1
	 * @param Keyvalue2 the keyValue 2
	 * @param Keyvalue3 the keyValue 3
	 * @param Keyvalue4 the keyValue 4
	 * @param Keyvalue5 the keyValue 5
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void WriteIntoPropertyFile(String Keyvalue1,String Keyvalue2,String Keyvalue3,String Keyvalue4, String Keyvalue5) throws IOException//, //ConfigurationException  
    {
		
		Properties prop = new Properties();
		OutputStream output = null;
		try 
		{
				
		filename = ConstantsProperties.ClientPropFile;
		output = new FileOutputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\"+filename);    
      	prop.setProperty("client_id",Keyvalue1) ;
		prop.setProperty("client_secert",Keyvalue2) ;
		prop.setProperty("Access_Token",Keyvalue3) ;
		prop.setProperty("ID",Keyvalue4) ;
		prop.setProperty("Access_Token_WPS",Keyvalue5) ;    	
		
		prop.store(new FileWriter(filename), "Client.properties");
	
		prop.store(output, "Written Client Details To File...");
	
		}
		catch (IOException io)
		{
			io.printStackTrace();
		} 
		finally
		{
			if (output != null)
			{
				try
				{					
					output.close();				
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		}		
    }
	
	/**
	 * Methods - Gets The Property From Property File
	 *
	 * @param key the key
	 * @return the property
	 */
	public static String getproperty(String key)
	{
		return props.getProperty(key);
	}
	
	/**
	 * Methods - Gets The Property From Customer Details Property File
	 *
	 * @param key the key
	 * @return the Property Val
	 */
	public static String getpropertyval(String key)
	{
		System.out.println("In this Method : getpropertyval "+key);
		return propval.getProperty(key);
	}
	
	/*
	 * 
	 * //Fluent Wait
	public WebElement fluentWait(final By locator){
	    Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
	        .withTimeout(30, TimeUnit.SECONDS)
	        .pollingEvery(5, TimeUnit.SECONDS)
	        .ignoring(NoSuchElementException.class);

	    WebElement foo = wait.until(
	        new Function<WebDriver, WebElement>() {
	            public WebElement apply(WebDriver driver) {
	                return driver.findElement(locator);
	            }
	        }
	    );
	    return foo;
	};*/
	
	/**
	 * Method - Test URL.
	 *
	 * @param url the URL
	 * @return the Int
	 * @throws InterruptedException the interrupted exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static int TestUrl(String url) throws  InterruptedException, IOException 
    {
		int code=0;
		System.out.println("Url "+url);
		try
		{
			URL u = new URL (url);
			HttpURLConnection huc =  ( HttpURLConnection ) u.openConnection (); 
			huc.setRequestMethod ("GET");   
			huc.connect () ; 
			code = huc.getResponseCode() ;
			System.out.println(code);
			if (code==200)
			{
				System.out.println("Url Up And Running");
			}
		}
	    catch (Exception e)
		{
			Assert.fail("Url Down");
		}
		return code;
	}
		
	
	/**
	 * Method - Browser Launch.
	 *
	 * @param driver the driver
	 * @param Browser the browser
	 * @return the string
	 * @throws InterruptedException the interrupted exception
	 */
	@Parameters("Broswer")
	public static String BrowserLaunch(WebDriver driver, String Browser) throws  InterruptedException
	{
		try
		{
			System.out.println("Reusable Method : In  Browser Launch :"+Browser);
			if (Browser.equalsIgnoreCase("firefox"))
			{	
				driver = new FirefoxDriver();
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(500,TimeUnit.SECONDS);	
				System.out.println("Successfully Opened " + Browser);
			}
			else if (Browser.equalsIgnoreCase("ie"))
			{
		        System.setProperty("webdriver.ie.driver", String.valueOf(System.getProperty("user.dir"))+"\\IEDriverServer.exe");
		        driver = new InternetExplorerDriver();
		        driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(500,TimeUnit.SECONDS);	
				System.out.println("Successfully Opened " + Browser);
		     } 
			 else if (Browser.equalsIgnoreCase("chrome")) 
			 {
				 System.setProperty("webdriver.chrome.driver", String.valueOf(System.getProperty("user.dir"))+"\\chromedriver.exe");
		         ChromeOptions options = new ChromeOptions();
                 options.addArguments("start-maximized");
		         driver = new ChromeDriver(options);
		         //driver.manage().window().maximize();
				 driver.manage().timeouts().implicitlyWait(500,TimeUnit.SECONDS);
				 System.out.println("Successfully Opened " + Browser);
		     }	     	
		}
		catch(Exception e)
		{
			System.out.println("UnSuccessfull In Opening " + Browser);
		}
		return Browser;
	}	
	
	/**
	 * Method - Open URL
	 *
	 * @param driver the driver
	 * @param Url the url
	 * @return the string
	 * @throws InterruptedException the interrupted exception
	 */
	public static String OpenUrl(WebDriver driver,String Url) throws  InterruptedException
    {
		try
		{
			System.out.println("Url........"+Url);
			driver.get(Url);
			System.out.println("Successfully Opened " + Url);
		}
		catch(Exception e)
		{
			System.out.println("UnSuccessfull In Opening " + Url);
		}
		return Url;
    }
	
	/**
	 * Method - Get Text
	 *
	 * @param element the element
	 * @return the string
	 */
	public static String GetText(WebElement element)
	{		
		String Text="No Value";
		try
		{  
			 WebElement Element = element;			 
			 if(Element != null)
				Text = element.getText();
		 }
		 catch (Exception e)
		 {
			 e.printStackTrace();
		 }
		 return Text;		
	}
	
	
	/**
	 * Method - Verify Element Contains Text
	 *
	 * @param element the element
	 * @param compareValue the compare value
	 */
	public static void VerifyContainsText(WebElement element,String compareValue){
	try
	{
			WebElement Welement= element;
			String value=Welement.getText();
			System.out.println("Value : "+value);
			if(value.contains(compareValue))
			{				
				//logMessage(compareValue+" should display.", compareValue+" is displayed.", "pass");
				//result = true;
			}
			else
			{
				//logMessage(compareValue+" should display.", compareValue+" is not displayed.", "fail");
			}
	}
	catch(Exception e)
	{
			//logMessage(compareValue+" should display.","Exception occured. Exception : "+e.getMessage(), "fail");
	}
			//return compareValue;		
	}
	
	/**
	 * Method - Verify Contains Text
	 *
	 * @param Response the response
	 * @param compareValue the compare value
	 */
	public static void VerifyContainsText(String Response,String compareValue)
	{
	try
	{
			System.out.println("Response   :::;"+Response);
			if(Response.contains(compareValue))
			{				
				logMessage(compareValue+" should display.", compareValue+" is displayed.", "pass");				
			}
			else
			{
				logMessage(compareValue+" should display.", compareValue+" is not displayed.", "fail");
			}
		}catch(Exception e){
			logMessage(compareValue+" should display.","Exception occured. Exception : "+e.getMessage(), "fail");
		}		
	}
	
	/**
	 * Method - Verify Exact Text.
	 *
	 * @param element the element
	 * @param compareValue the compare value
	 */
	public static void VerifyExactText(WebElement element,String compareValue){
	try
	{
		WebElement Welement= element;
		String value=Welement.getText();
		System.out.println("Value : "+value);
		if(value.equalsIgnoreCase(compareValue))
		{				
			logMessage(compareValue+" should display.", compareValue+" is displayed.", "pass");
		}
		else
		{
			logMessage(compareValue+" should display.", compareValue+" is not displayed.", "fail");
		}
	}catch(Exception e){
		logMessage(compareValue+" should display.","Exception occured. Exception : "+e.getMessage(), "fail");
	}
	}
	
	/**
	 * Method - Verify Exact API Response Text.
	 *
	 * @param response the response
	 * @param compareValue the compare value
	 */
	public static void VerifyExactRespText(String response,String compareValue){
	try
	{
		String value=response;
		System.out.println("Value : "+value);
		if(value.equalsIgnoreCase(compareValue))
		{				
			logMessage(compareValue+" should display.", compareValue+" is displayed.", "pass");
		}
		else
		{
			logMessage(compareValue+" should display.", compareValue+" is not displayed.", "fail");
		}
	}catch(Exception e){
		logMessage(compareValue+" should display.","Exception occured. Exception : "+e.getMessage(), "fail");
	}	
	}
	
	/**
	 * Method - Verify HTML tag text.
	 *
	 * @param htmlresponse the HtmlResponse
	 * @param Startindex the StartIndex
	 * @param starttag the StartTag
	 * @param endtag the EndTag
	 */
	public static void VerifyHTMLTagText(String htmlresponse,int Startindex, String starttag,String endtag)
	{		
	try
	{
	//System.out.println("hello here 1 ");
	String html = htmlresponse;
	String title = html.substring(html.indexOf(starttag) + Startindex, html.indexOf(endtag));
	//System.out.println("hello here 3 ");
	//System.out.println("Verify results for H1 tag : "+title);
	}
	catch(Exception e)
	{
	
	}
	}
	
	/**
	 * Method - Open In New Tab In Browser
	 *
	 * @param driver the Driver
	 * @param Url the URL
	 */
	public static void OpenInNewTab(WebDriver driver, String Url)
	{
		try
		{
			String parentWindowHandle = driver.getWindowHandle();
			for(String winHandle : driver.getWindowHandles())
			{
				if(!parentWindowHandle.contains(winHandle))
				driver.switchTo().window(winHandle);
				//operation
				((JavascriptExecutor) driver).executeScript("window.open('"+Url+"')");			
			}
			driver.switchTo().window(parentWindowHandle);			
		}
		catch(Exception e)
		{
		
		}		
	}
	
	
	
}
