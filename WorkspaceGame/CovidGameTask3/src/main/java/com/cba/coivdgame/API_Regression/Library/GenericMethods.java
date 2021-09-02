package com.cba.coivdgame.API_Regression.Library;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class GenericMethods
{
	public static void FormatURL(String myURL)
	{
		//String myURL="http://echo.jsontest.com/key/value/one/two";
		try
		{
			URL url = new URL(myURL);
		    String nullFragment = null;
		    URI uri = new URI(url.getProtocol(), url.getHost(), url.getPath(), url.getQuery(), nullFragment);
		   
		} 
		catch (MalformedURLException e)
		{
		      System.out.println("URL " + myURL + " Not Formatted");
	    } 
		catch (URISyntaxException e)
		{
		      System.out.println("URI " + myURL + " Not Formatted");
		}
		//String myresponse = HttpClientHelper.getUrl(myURL);
		//System.out.println(myresponse);
		//API1_Fields requirements = TestHelper.gsonBuilder ().fromJson (HttpClientHelper.getUrl (myURL), API1_Fields.class);
		//System.out.println(" json  getkey "+requirements.getkey());
		//System.out.println(" json  getone "+requirements.getone());
		//return myresponse;
	}

}
