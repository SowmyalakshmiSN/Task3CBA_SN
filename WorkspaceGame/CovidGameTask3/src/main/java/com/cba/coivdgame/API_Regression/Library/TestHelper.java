package com.cba.coivdgame.API_Regression.Library;


import static com.cba.coivdgame.API_Regression.Library.CareerHttpClient.Auth.ASID;

//import static Test_Library.Environment.useSauceLabs;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.cookie.BasicClientCookie;

import com.cba.coivdgame.API_Regression.Library.CareerHttpClient.Auth;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TestHelper
{
	//Declare String DateFormat With Some Date Format
	public final static String dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS";

    /**
     * Create a string of <size> chars
     * 
     * @param size
     *            The number of characters in a string
     * @return The string of <size> chars
     */
    public synchronized static String dummyString (int size)
    {
        char[] chars = {
        				'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
        				'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
        				'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
        				'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
                       };
        return getRandomStringFromCharArray (size, chars);
    }

    private synchronized static String getRandomStringFromCharArray (int size, char[] chars)
    {
        return RandomStringUtils.random (size, chars);
    }

    //Declare Method Gson gsonBuilder To Return The Desired DateFormat
    public synchronized static Gson gsonBuilder ()
    {
        return new GsonBuilder ().setDateFormat (dateFormat).create ();
    }

    private synchronized static org.openqa.selenium.Cookie setAuthCookie (Cookie cookie)
    {
        if (cookie == null)
        {
            Logging.error ("getting a null cookie");
            return null;
        } 
        else 
        {
            Logging.info ("name: " + cookie.getName ());
            Logging.info ("value: " + cookie.getValue ());
            Logging.info ("domain: " + cookie.getDomain ());
            Logging.info ("path: " + cookie.getPath ());
            Logging.info ("expire: " + cookie.getExpiryDate ());
            Logging.info ("secure: " + cookie.isSecure ());
            Logging.info ("httpOnly: " + cookie.isPersistent ());

            Calendar expire = Calendar.getInstance ();
            expire.set (Calendar.YEAR, Calendar.getInstance ().get (Calendar.YEAR) + 2);

            // this is for Safari, domain=null and IE, secure=false
            org.openqa.selenium.Cookie sso = new org.openqa.selenium.Cookie (cookie.getName (),
                                                                             cookie.getValue (),
                                                                             APIEnvironment.domain,
                                                                             "/",
                                                                             expire.getTime (),
                                                                             false,
                                                                             false);
            return sso;
        }
    }

    public synchronized static List <org.openqa.selenium.Cookie> setAuthCookies (List <Cookie> cookies) 
    {
        List <org.openqa.selenium.Cookie> sso = new ArrayList <org.openqa.selenium.Cookie> ();
        if (cookies.size () == 0)
        {
            Logging.error ("getting no cookies");
        } 
        else
        {
            for (Cookie cookie : cookies)
                sso.add (setAuthCookie (cookie));
        }
        return sso;
    }
    
    public synchronized static void refreshCookies (List <org.openqa.selenium.Cookie> cookies) {
        try {
            List <Cookie> apacheCookies = new ArrayList <Cookie> ();
            for (org.openqa.selenium.Cookie cookie : cookies)
                if (!cookie.getName ().equals (ASID.name ()))
                    apacheCookies.add (new BasicClientCookie (cookie.getName (), cookie.getValue ()));

            String url = APIEnvironment.baseUrl + "/api/authentication-service/2/" + APIEnvironment.tenant + "/user/refresh";
            Map <String, Object> result = CareerHttpClient.post (url, "", apacheCookies);
            for (Auth auth : Auth.values ())
                if (result.get (auth.name ()) != null) {
                    Cookie apacheCookie = (Cookie) result.get (auth.name ());
                    Iterator <org.openqa.selenium.Cookie> i = cookies.iterator ();
                    while (i.hasNext ())
                        if (i.next ().getName ().equals (apacheCookie.getName ()))
                            i.remove ();

                    cookies.add (setAuthCookie (apacheCookie));
                }
        } catch (Exception e) {
            Logging.error (e.getMessage ());
        }
    }
    /*
    public synchronized static Location getUserLocation () {
        if (useSauceLabs)
            return new Location ("San Francisco", "CA", "94107");
        String url = APIEnvironment.baseUrl + "/api/validation-service/1/uopx/address/ipaddr";
        return new Gson ().fromJson (CareerHttpClient.getUrl (url), Location.class);
    }*/

}
