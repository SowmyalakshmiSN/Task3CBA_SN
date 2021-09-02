package com.cba.coivdgame.API_Regression.Library;

import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

// This class reads the POM.xml and command-line arguments that initialize the test environment
public class Environment {

    public static Long                           maxWaitTime     = new Long (30);
    public static Long                           jsScriptTime    = new Long (2000);
    public static Boolean                        useSauceLabs;
    public static Boolean                        verbose;
    public static URL                            sauceUrl;
    public static String                         sauceUser;
    public static String                         sauceKey;
    public static String                         sauceTunnel;
    public static String                         testUser;
    public static String                         testPassword;
    public static String                         testVersion;
    public static String                         baseUrl;
    public static int                            threadCount;
    public static String                         parallel;
    public static int                            browserWidth    = 1000;
    public static int                            browserHeight   = 700;
    public static BrowserType                    browserType;
    public static String                         platform;
    public static String                         version;
    public static String                         os;
    public static String                         deviceName;
    public static String                         id;
    public static String                         chromeDriverPath;
    public static String                         ieDriverPath;
    public static DeviceOrientation              deviceOrientation;
    public static ConcurrentLinkedQueue <String> windowPositions = new ConcurrentLinkedQueue <String> ();
    public static Map <String, String>           properties;

    public static void initialization () throws Exception {
        System.setProperty ("apple.awt.UIElement", "true");
        useSauceLabs = Boolean.valueOf (System.getProperty ("useSauceLabs", "false"));
        maxWaitTime = Long.valueOf (System.getProperty ("test.maxWaitTime", "30"));
        if (useSauceLabs) {
            maxWaitTime *= 3; // everything runs slower on SauceLabs
        }
        id = System.getProperty ("env", "qa").toLowerCase ();
        platform = System.getProperty ("platform");
        os = System.getProperty ("os.name").toLowerCase ();
        browserType = BrowserType.valueOf (System.getProperty ("browser", "Chrome").toLowerCase ());
        version = System.getProperty ("version");
        deviceName = System.getProperty ("deviceName");
        deviceOrientation = DeviceOrientation.valueOf (System.getProperty ("device-orientation", "Landscape").toLowerCase ());
        sauceUser = System.getProperty ("sauce.user", "saucetestApti");
        sauceKey = System.getProperty ("sauce.key", "3664a606-3880-4daa-9a31-19d24950d9ac");
        sauceTunnel = System.getProperty ("sauce.tunnel", sauceUser);
        baseUrl = System.getProperty ("test.url");
        testUser = System.getProperty ("test.user");
        testPassword = System.getProperty ("test.pass");
        testVersion = System.getProperty ("test.version");
        parallel = System.getProperty ("parallel", "methods");
        threadCount = Integer.parseInt (System.getProperty ("threadCount", "1"));
        verbose = Boolean.valueOf (System.getProperty ("verbose", "false"));
        if (threadCount > 1) {
            verbose = false;
        }
        chromeDriverPath = System.getProperty ("chromedriver.path");
        ieDriverPath = System.getProperty ("iedriver.path");
        sauceUrl = new URL ("http://" + sauceUser + ":" + sauceKey + "@ondemand.saucelabs.com:80/wd/hub");

        if (!useSauceLabs) {
            setBrowserSizeAndPosition ();
            if (System.getProperty ("browserWidth") != null) {
                browserWidth = Integer.parseInt (System.getProperty ("browserWidth"));
            }
            if (System.getProperty ("browserHeight") != null) {
                browserHeight = Integer.parseInt (System.getProperty ("browserHeight"));
            }
        }

        properties = new HashMap <String, String> ();
        InputStream stream = ClassLoader.getSystemResourceAsStream ("env.properties");
        if (stream != null) {
            Properties envProperties = new Properties ();
            envProperties.load (stream);
            for (String key : envProperties.stringPropertyNames ()) {
                if (key.startsWith (id.toLowerCase () + ".")) {
                    properties.put (key.replace (id.toLowerCase () + ".", "").trim (), envProperties.getProperty (key).trim ());
                }
            }
        }
    }

    private static void setBrowserSizeAndPosition () throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel (UIManager.getSystemLookAndFeelClassName ());
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment ();
        Rectangle bounds = ge.getMaximumWindowBounds ();
        int maxWidth = 1100;
        int screenWidth = bounds.width;
        int screenHeight = bounds.height;
        int left = bounds.x;
        int top = bounds.y;
        int rows = 1;
        int columns = 1;
        if (threadCount > 1) {
            rows = 2;
        }
        if (threadCount > 2) {
            columns = 2;
        }
        if (threadCount > 4) {
            columns = 3;
        }
        if (threadCount > 6) {
            rows = 3;
        }
        if (threadCount > 9) {
            columns = 4;
        }
        if (threadCount > 12) {
            rows = 4;
        }
        browserWidth = screenWidth / columns;
        if (browserWidth > maxWidth) {
            browserWidth = maxWidth;
        }

        browserHeight = screenHeight / rows;
        for (int i = 0; i < threadCount; i++) {
            if (i > 0 && i % columns == 0) {
                left = bounds.x;
                top += browserHeight;
            }
            windowPositions.add (left + "," + top);
            left += browserWidth;
        }
    }

    public static enum BrowserType {
        ie, ff, chrome, safari, opera, ipad, iphone, android;
    }

    public static enum DeviceOrientation {
        portrait, landscape;
    }
}
