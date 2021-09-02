package com.cba.coivdgame.Web_Regression.Library;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.varia.NullAppender;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.Assert;

public class SuperTestNG_WEB
{
	//Declaring Global Variables
	protected WebDriver driver;
	static String env_name;
	static String filename;
	static ClassLoader loader; 
	static Properties props;
	static InputStream resourceStream;
			
		@BeforeSuite
	@Parameters("env")	
	public void driverSetup(String env) throws  InterruptedException 
    {			
        Reusable.Setupdriver(env); 	
        //Reusable.ClientPropFile();        
    }	
		
	@BeforeClass
	@Parameters({"env","url"})
	public void TestUrl(String env, String url) throws  InterruptedException, IOException 
    {
		Reusable.Setupdriver(env);
		//Reusable.ClientPropFile();
		String Url = Reusable.getproperty(url);
		System.out.println("Url "+Url);
		try
		{
			URL u = new URL (Url);
			HttpURLConnection huc =  ( HttpURLConnection ) u.openConnection (); 
			huc.setRequestMethod ("GET");   
			huc.connect () ; 
			int code = huc.getResponseCode() ;
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
    }
		
	@BeforeMethod
	@Parameters("Broswer")
	public void LaunchBrowser(String Browser) throws  InterruptedException
	{
		//Thread.sleep(5000);
		try
		{
			System.out.println("Before Method : In Launch Browser  :"+Browser);
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
	}	
			
	//Open URL
	//public void OpenUrl(String Url) throws  InterruptedException 
	public static String OpenUrl(WebDriver driver,String Url) throws  InterruptedException
    {
		try
		{
			//driver.get(Reusable.getproperty(Url));
			driver.get(Url);
			//driver.manage().timeouts().pageLoadTimeout(500,TimeUnit.SECONDS);
			System.out.println("Successfully Opened " + Url);
			//org.apache.log4j.BasicConfigurator.configure(new NullAppender());
			//Logger.getRootLogger().setLevel(Level.WARN);
		}
		catch(Exception e)
		{
			System.out.println("UnSuccessfull In Opening " + Url);
		}
		return Url;
    }
	
	@AfterTest
	public void TearDown() 
    {
		String operatingSystem = System.getProperty("os.name");
        try 
        {
            if (operatingSystem.contains("Windows"))
            {
                Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");
                Runtime.getRuntime().exec("taskkill /F /IM iexplore.exe");
                Runtime.getRuntime().exec("taskkill /F /IM firefox.exe");
                Runtime.getRuntime().exec("taskkill /F /IM safari.exe");
                Runtime.getRuntime().exec("taskkill /F /IM opera.exe");
                System.out.println("Successfully Closed All The Browsers");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
