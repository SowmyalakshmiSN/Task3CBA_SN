package com.cba.coivdgame.Web_Regression.Library;

import java.io.InputStream;
import java.util.Properties;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;


// TODO: Auto-generated Javadoc
/**
 * The Class SuperTestNG Created To Execute Other TestNG Annotations Prior Running @Test.
 */
public class SuperTestNG_API 
{
	//Declaring Global Variables
	/** The env name. */
	static String env_name;
	
	/** The filename. */
	static String filename;
	
	/** The loader. */
	static ClassLoader loader; 
	
	/** The props. */
	static Properties props;
	
	/** The resource stream. */
	static InputStream resourceStream;
	
		
	/**
	 * Driver Setup Method Created To Execute In @BeforeSuite To Set The Environment On Which Scripts Need to Be Run. 
	 * As Specified In TestNG XML.
	 * @param env the env
	 * @throws InterruptedException the interrupted exception
	 */
	@BeforeSuite
	@Parameters("env")	
	public void driverSetup(String env) throws  InterruptedException 
    {	
		Reusable.Setupdriver(env); 	
        //Reusable.ClientPropFile();
    }	
			
}