package com.cba.coivdgame.API_Regression.Library;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.SSLContext;
import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import com.reports.CustomReporter;


/**
 * The Class HttpClientHelper.
 */

public class HttpClientHelper
{
	/** Global Variables */
	/** The headers. */
	private static List <Header> headers;	
		
	
	/**
	 * Method - Add Static Header To API
	 *
	 * @param expression the expression
	 */
	public static void Addheader(String expression)
	{
		//System.out.println("expression   :::: "+expression);
		try
		{
		headers = new ArrayList <Header> ();
		   
		String[] expression1 = expression.split(",");
		
		 for (int i=0; i<expression1.length; i++)
	     {			
			String[] exp= expression1[i].split("=");		
			headers.add (new BasicHeader (exp[0], exp[1]));					
	     }
		}
		catch(Exception e)
		{
			System.out.println("API Header : "+headers+" Not Added To Headers List");
		}
	}
	
	/**
	 * UnUsed
	 *
	 * @param expression the expression
	 */
	public static void AddheaderKV(String expression)
	{
		//System.out.println("Access Token    :::: "+expression);
		try
		{
		headers = new ArrayList <Header> ();
		   
		String[] expression1 = expression.split(",");
	
		 for (int i=0; i<expression1.length; i++)
	     {
			
			String[] exp= expression1[i].split("=");		
			exp[1]= "Basic "+exp[1];
			exp[1]= exp[1].concat("=");
			headers.add (new BasicHeader (exp[0], exp[1]));
		 }
		}
		catch(Exception e)
		{
			System.out.println("API Header : "+headers+" Not Added To Headers List");
		}		
	}
	
	/**
	 * Method - Add Static Header With Out Body
	 *
	 * @param expression the expression
	 */
	public static void AddheaderWB(String expression)
	{
		try
		{
		headers = new ArrayList <Header> ();
		   
		String[] expression1 = expression.split(",");
		for (int i=0; i<expression1.length; i++)
	     {
			String[] exp= expression1[i].split("=");
			headers.add (new BasicHeader (exp[0], exp[1]));
			//System.out.println("API Header : "+headers+" Added Successfully To Headers List");
	     }
		}
		catch(Exception e)
		{
			System.out.println("API Header : "+headers+" Not Added To Headers List");
		}
	}
	
	/**
	 * Method - Add Dynamic Header.
	 *
	 * @param expression the expression
	 */
	public static void Adddynamicheader(String expression)
	{
		headers = new ArrayList <Header> ();
		  
		String[] expression1 = expression.split(",");
		for (int i=0; i<expression1.length; i++)
	     {
			String[] exp= expression1[i].split("=");
			headers.add (new BasicHeader (exp[0], exp[1]));	
		 }
	}
	
	
	/**
	 * Method - Add Dynamic Header.
	 *
	 * @param expression the expression
	 */
	public static void AdddynamicheaderN(String expression)
	{
		try
		{
			String Key = "Authorization";   
			headers.add (new BasicHeader (Key, expression));	
			System.out.println("API Header : "+headers+" Added Successfully To Headers List");
		}
		catch (Exception e)
		{
			System.out.println("API Header : "+headers+" Not Added To Headers List");
		}
	}
	
	/**
	 * Method - Add Dynamic Header.
	 *
	 * @param expression the expression
	 */
	public static void AdddynamicheaderNN(String expression)
	{
		try
		{
			System.out.println("Hello in AdddynamicheaderNN");
			String Key = "Authorization"; 
			expression = "Basic "+expression;
			expression = expression+"=";
			headers.add (new BasicHeader (Key, expression));	
			System.out.println("API Header : "+headers+" Added Successfully To Headers List");
		}
		catch (Exception e)
		{
			System.out.println("API Header : "+headers+" Not Added To Headers List");
		}
	}
	
	/**
	 * Method - MyGetURL
	 *
	 * @param url the url
	 * @param headValue the head value
	 * @param list the list
	 * @return the string
	 */
	//Get Method With Headers - Customised
    public synchronized static String MygetUrl (String url, String headValue, List<String> list)
    {
        String result;
        System.out.println("This MygetUrl : ");
        try
        {        
            HttpGet httpGet = new HttpGet (url);
            
            Addheader(headValue);
            
            for (Header header : headers)
            {
                httpGet.addHeader (header);
                System.out.println("Added Header : "+header);
            }
            Logging.info ("Executing request: " + httpGet.getRequestLine ());
            
            result = execute (httpGet);
           
            System.out.println("Response :::"+result);
            
           /* String html = result;
    		String title = html.substring(html.indexOf("<h1>") + 4, html.indexOf("</h1>"));
    		System.out.println("Verify results for H1 tag : "+title);*/
        } 
        catch (Exception e)
        {
            Logging.info ("Error: " + e.getMessage ());
            throw new RuntimeException (e.getCause ());
        }
        return result;
    }
    
  /**
   * Myget url HTML.
   *
   * @param url the url
   * @param headValue the head value
   * @param list the list
   * @return the string
   */
  //Get Method With Headers - Customised
    public synchronized static String MygetUrlHTML (String url, String headValue, List<String> list)
    {
        String result;
        System.out.println("This MygetUrlHTML : ");
        try
        {        
            HttpGet httpGet = new HttpGet (url);
            
            Addheader(headValue);
            
            for (Header header : headers)
            {
                httpGet.addHeader (header);
                System.out.println("Added Header : "+header);
            }
            Logging.info ("Executing request: " + httpGet.getRequestLine ());
            
            result = execute (httpGet);
           
            System.out.println("Response :::"+result);
            
           // Reusable.VerifyHTMLTagText(result, 4, "<h1>","</h1>");
           // System.out.println("hello 1");
            /*String html = result;
    		String title = html.substring(html.indexOf("<h1>") + 4, html.indexOf("</h1>"));
    		System.out.println("Verify results for H1 tag : "+title);*/
        } 
        catch (Exception e)
        {
            Logging.info ("Error: " + e.getMessage ());
            throw new RuntimeException (e.getCause ());
        }
        return result;
    }
    
  /**
   * Myget url N.
   *
   * @param url the url
   * @param headValue the head value
   * @param list the list
   * @return the string
   */
  //Get Method With Dynamic Headers - Customized
    public synchronized static String MygetUrlN (String url, String headValue, List<String> list)
    {
        String result;
        System.out.println("This MygetUrlN : ");
        try
        {        
        	//System.out.println("Url in MygetUrlN 1:"+url);
            HttpGet httpGet = new HttpGet (url);
            //System.out.println("Url in MygetUrlN 2:"+url);
            Addheader(headValue);
            for (Header header : headers)
            {
                httpGet.addHeader (header);
                System.out.println("Headers in MygetUrlN :"+header);
            }
            Logging.info ("Executing request: " + httpGet.getRequestLine ());
           // System.out.println("Headers in MygetUrlN :"+ "Here1");
           // System.out.println("httpGet in MygetUrlN :"+httpGet);
            //System.out.println("result in MygetUrlN :"+(execute (httpGet)));
            
           // result = execute (httpGet);
            result =  Getexecute(httpGet);
           // System.out.println("Headers in MygetUrlN :"+ "Here2");
           System.out.println("Response From API : MygetUrlN :"+ result);
            
        } 
        catch (Exception e)
        {
            Logging.info ("Error: " + e.getMessage ());
            throw new RuntimeException (e.getCause ());
        }
        return result;
    }
    
	
    /**
     * Myget url.
     *
     * @param url the url
     * @return the string
     */
    //Get Method With Out Headers - Customised
    public synchronized static String MygetUrl (String url)
    {
    	String result;
    	try
        {        
    		System.out.println("This MygetUrl : ");
    		HttpGet httpGet = new HttpGet (url);                    
            Logging.info ("Executing request: " + httpGet.getRequestLine ());
           
            result = execute (httpGet);
            System.out.println("Response From API : "+result);
            CustomReporter.log("Response From API : "+result);
        } 
        catch (Exception e)
        {
            Logging.info ("Error: " + e.getMessage ());
            //CustomReporter.errorLog("Error: " + e.getMessage ());
            throw new RuntimeException (e.getCause ());
        }
        return result;
    }
    	
    
    /**
    * My post url.
    *
    * @param url the url
    * @param headValue the head value
    * @param body the body
    * @param list the list
    * @return the string
    */
   //Post Method With Headers - Customised
    public synchronized static String MyPostUrl (String url,String headValue, String body, List<String> list)
    {
    	String result;
        try 
        {
        	
        	HttpPost httpPost = new HttpPost (url);
            
            //Added
            Addheader(headValue);
            //AddheaderKV(headValue);
           
            for (Header header : headers)
            {
                httpPost.addHeader (header);
               // System.out.println("Added Header : "+header);
            }                 
            
            httpPost.setEntity (new StringEntity (body));
            //System.out.println("Body ::"+body);
            result = execute (httpPost);
            
            System.out.println("Response From API : "+result);
        } 
        catch (Exception e)
        {
            Logging.info ("Error: " + e.getMessage ());
            throw new RuntimeException (e.getCause ());
        }
        return result;
    }
    
     
   /**
      * Method - My Post - Dynamic Body
      *
      * @param url the url
      * @param headValue the head value
      * @param postParameters the post parameters
      * @return the string
      */
    public synchronized static String MyPostUrlN (String url,String headValue,ArrayList<NameValuePair>postParameters)
    {
    	String result;
    	try 
        {
        	
        	HttpPost httpPost = new HttpPost (url);
           
            //Added
            Addheader(headValue);
           
            for (Header header : headers)
            {
                httpPost.addHeader (header);
               // System.out.println("Added Header : "+header);
            }                 
            
            httpPost.setEntity(new UrlEncodedFormEntity(postParameters));
            //System.out.println("Body ::"+postParameters);
            result = execute (httpPost);
           
            System.out.println("Response From API : "+result);
        } 
        catch (Exception e)
        {
            Logging.info ("Error: " + e.getMessage ());
            throw new RuntimeException (e.getCause ());
        }
        return result;
    }
    
    /**
     * Method - My Post
     *
     * @param url the URL
     * @param body the Body
     * @param list the List
     * @return the String
     */
     public synchronized static String MyPostUrlNN (String url,String body,List<String> list)
     {
     	String result;
     	try 
         {
         	
         	HttpPost httpPost = new HttpPost (url);
             
             
             
             for (Header header : headers)
             {
                 httpPost.addHeader (header);
                 //System.out.println("Added Header : "+header);
             }                 
             
            httpPost.setEntity (new StringEntity (body));
            // System.out.println("Body ::"+body);
             result = execute (httpPost);
            
             System.out.println("Response From API : "+result);
         } 
         catch (Exception e)
         {
             Logging.info ("Error: " + e.getMessage ());
             throw new RuntimeException (e.getCause ());
         }
         return result;
     }
     
  /**
   * Method - My Post
   *
   * @param url the URL
   * @param body the Body
   * @param list the List
   * @return the String
   */
    public synchronized static String MyPostUrlN (String url, String body, List<String> list)
    {
    	String result;
        try 
        {
        	System.out.println("Hello In  MyPostUrlN: ");
            HttpPost httpPost = new HttpPost (url);
            for (Header header1 : headers)
            {
	              httpPost.addHeader (header1);
	              System.out.println("Added Header MyPostUrlN : "+header1);
	        }                  
            httpPost.setEntity (new StringEntity (body));      
           result = execute (httpPost);          
           System.out.println("Response From API : "+ result);
        } 
        catch (Exception e)
        {
            Logging.info ("Error: " + e.getMessage ());
            throw new RuntimeException (e.getCause ());
        }
        return result;
    }
    
    /**
     * Method Added Now - For Post Method With Headers & WithOut Body- Customized
     */
    private static class ContentLengthHeaderRemover implements HttpRequestInterceptor
    {
   	    public void process(HttpRequest request, HttpContext context) throws HttpException, IOException
   	    {
	        request.removeHeaders(HTTP.CONTENT_LEN);
	        System.out.println("fighting org.apache.http.protocol.RequestContent's ProtocolException "+ "Content-Length header already present");
	    }
	}    
    
    /**
     * My Post URL
     *
     * @param url the URL
     * @param headValue the Header Value
     * @param list the List
     * @return the String
     */
    public synchronized static String MyPostUrl (String url,String headValue,List<String> list)
	{ 
		String result;
		try 
	    {
	       HttpPost httpPost = new HttpPost (url);
	       HttpClientBuilder httpClientBuilder = HttpClients.custom();
		   httpClientBuilder.addInterceptorFirst(new ContentLengthHeaderRemover());
		   AddheaderWB(headValue);
	       for (Header header : headers)
	        {
	            httpPost.addHeader (header);
	           System.out.println("Added Header : " +header);
	        }    
	        result = execute (httpPost);
	        System.out.println("Response From API : "+ result);
	    } 
	    catch (Exception e)
	    {
	        Logging.info ("Error: " + e.getMessage ());
	        throw new RuntimeException (e.getCause ());
	    }
	   return result;
	 }
    
    /**
     * Method - My Delete
     *
     * @param url the URL
     * @return the String
     */
    public synchronized static String MyDeleteUrl (String url)
    {
    	String result;
        try
        {
            HttpDelete httpDelete = new HttpDelete (url);
            Logging.info ("Executing request: " + httpDelete.getRequestLine ());
            result = execute (httpDelete);
           System.out.println("Response From API : "+result);
          //  CustomReporter.log("Response From API : "+result);
        } 
        catch (Exception e) 
        {
            Logging.info ("Error: " + e.getMessage ());
            CustomReporter.errorLog(e.getMessage ());
            throw new RuntimeException (e.getCause ());
        }
        return result;
    }
    
    /**
     * Method - My Delete URL
     *
     * @param url the URL
     * @param headValue the Header Value
     * @param list the List
     * @return the String
     */
    public synchronized static String MyDeleteUrl (String url, String headValue, List<String> list)
    {
        String result;
        System.out.println("Hello in MyDeleteUrl");
        try
        {
            HttpDelete httpDelete = new HttpDelete (url);
          
            Addheader(headValue);
          
            for (Header header : headers)
            {
                httpDelete.addHeader (header);
                System.out.println("Added Header : "+header);
            }
           
            Logging.info ("Executing request: " + httpDelete.getRequestLine ());
            
            result = execute (httpDelete);
                      
        } 
        catch (Exception e) 
        {
            Logging.info ("Error: " + e.getMessage ());
            throw new RuntimeException (e.getCause ());
        }
        return result;
    }

    /**
     * Method - My Put URL
     *
     * @param url the URL
     * @param headValue the Header Value
     * @param json the JSon
     * @param list the List
     * @return the String
     */
    public synchronized static String MyPutUrl (String url, String headValue, String json, List<String> list)
    {
        String result = null;
        System.out.println("Hello in MYputurl");
        try
        {
            HttpPut httpPut = new HttpPut (url);
           
            Addheader(headValue);
         
            for (Header header : headers)
            {
            	httpPut.addHeader (header);
            	System.out.println("Added Header : "+header);
            }
           
            System.out.println("Body in Put :: "+json);
            httpPut.setEntity (new StringEntity (json));
           
          
            result = Putexecute (httpPut);
          
            System.out.println("Response From API : "+ result);
        } 
        catch (Exception e) 
        {
            Logging.info ("Error: " + e.getMessage ());
            throw new RuntimeException (e.getCause ());
        }
        return result;
    }
    
    /**
     * Method - My Put URL
     *
     * @param url the URL
     * @param json the JSon
     * @param list the List
     * @return the String
     */
    public synchronized static String MyPutUrl (String url,String json, List<String> list)
    {
        String result = null;
        //System.out.println("Hello in MYputurl");
        try
        {
            HttpPut httpPut = new HttpPut (url);
           
            for (Header header : headers)
            {
            	httpPut.addHeader (header);
            	//System.out.println("Added Header : "+header);
            }
         
            //System.out.println("Body in Put :: "+json);
            httpPut.setEntity (new StringEntity (json));
           
         
            result = Putexecute (httpPut);
        
            System.out.println("Response From API : "+ result);
        } 
        catch (Exception e) 
        {
            Logging.info ("Error: " + e.getMessage ());
            throw new RuntimeException (e.getCause ());
        }
        return result;
    }

    /**
     * Method - PutExecute.
     *
     * @param request the request
     * @return the string
     */
    private synchronized static String Putexecute (HttpUriRequest request)
    {
        String result = null;
        try
        {
        	
            SSLContext sslcontext = SSLContexts.custom ().loadTrustMaterial (null, new TrustSelfSignedStrategy ()).build ();
            //System.out.println("sslcontext :"+sslcontext);
            
            RequestConfig globalConfig = RequestConfig.custom ().setCookieSpec (CookieSpecs.IGNORE_COOKIES).build ();
            //System.out.println("globalConfig :"+globalConfig);
            
            CloseableHttpClient client = HttpClients.custom ().setSslcontext (sslcontext).setDefaultRequestConfig (globalConfig).build ();
           // System.out.println("client :"+client);
           
            
            CloseableHttpResponse response = client.execute (request);
           
            //System.out.println("response in Execute: "+response);
            int status = response.getStatusLine ().getStatusCode ();
            System.out.println("Status in Execute: "+status);
            if (response.getEntity () != null)
            {
                result = EntityUtils.toString (response.getEntity ());
            }
            response.close ();
            client.close ();
        }
        catch (Exception e)
        {
            Logging.info ("Error: " + e.getMessage ());
            throw new RuntimeException (e.getCause ());
        }
        return result;
    }
    
    /**
     * Method - Get Execute.
     *
     * @param request the request
     * @return the string
     */
    private synchronized static String Getexecute (HttpUriRequest request)
    {
        String result = null;
        try
        {
            SSLContext sslcontext = SSLContexts.custom ().loadTrustMaterial (null, new TrustSelfSignedStrategy ()).build ();
            RequestConfig globalConfig = RequestConfig.custom ().setCookieSpec (CookieSpecs.IGNORE_COOKIES).build ();
            CloseableHttpClient client = HttpClients.custom ().setSslcontext (sslcontext).setDefaultRequestConfig (globalConfig).build ();
            CloseableHttpResponse response = client.execute (request);
            int status = response.getStatusLine ().getStatusCode ();
            System.out.println("Status in Execute: "+status);
            if (response.getEntity () != null)
            {
                result = EntityUtils.toString (response.getEntity ());
            }
            response.close ();
            client.close ();
        }
        catch (Exception e)
        {
            Logging.info ("Error: " + e.getMessage ());
            throw new RuntimeException (e.getCause ());
        }
        return result;
    }
    
    /**
     * Method - Execute.
     *
     * @param request the request
     * @return the string
     */
    private synchronized static String execute (HttpUriRequest request)
    {
        String result = null;
        try
        {
        	
        	SSLContext sslcontext = SSLContexts.custom ().loadTrustMaterial (null, new TrustSelfSignedStrategy ()).build ();
        	//System.out.println("sslcontext :"+sslcontext);
        
        	RequestConfig globalConfig = RequestConfig.custom ().setCookieSpec (CookieSpecs.IGNORE_COOKIES).build ();
        	//System.out.println("globalConfig :"+globalConfig);
        	
        	CloseableHttpClient client = HttpClients.custom ().setSslcontext (sslcontext).setDefaultRequestConfig (globalConfig).build ();
        	//System.out.println("client :"+client);
        
      
            CloseableHttpResponse response = client.execute (request);
           
            //System.out.println("response in Execute: "+response);
            int status = response.getStatusLine ().getStatusCode ();
            
            System.out.println("Status in Execute: "+status);
            if (response.getEntity () != null)
            {
                result = EntityUtils.toString (response.getEntity ());
            }
     
            response.close ();
            client.close ();
        }
        catch (Exception e)
        {
            Logging.info ("Error: " + e.getMessage ());
            throw new RuntimeException (e.getCause ());
        }
        return result;
    }
  
}
