package com.cba.coivdgame.API_Regression.Library;

import com.cba.coivdgame.API_Regression.Library.Environment;

public class APIEnvironment extends Environment {

    public static Boolean isMobile    = false;
    public static Boolean usingSafari = browserType.equals (BrowserType.safari);
    public static Boolean usingIE     = browserType.equals (BrowserType.ie);
    public static Boolean usingFF     = browserType.equals (BrowserType.ff);
    public static String  baseUrl;
    public static String  domain;
    public static String  tenant;

    public static void initialization () {
        baseUrl = (Environment.baseUrl == null ? "" : Environment.baseUrl);

        if (browserType.equals (BrowserType.android)
                || browserType.equals (BrowserType.ipad)
                || browserType.equals (BrowserType.iphone)) {
            isMobile = true; 
        }
    }
}
