package com.cba.coivdgame.API_Regression.Library;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.net.ssl.SSLContext;
import org.apache.http.Header;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;


public class CareerHttpClient extends HttpClientHelper {

    public final static String BODY = "BODY";

    public enum Auth {
        TOKEN, INFO, ASID, APOLLOASSERTION
    }

    public synchronized static Map <String, Object> post (String url, String body, List <Cookie> cookies) {
        Map <String, Object> result = new HashMap <String, Object> ();
        try {
            HttpPost httpPost = new HttpPost (url);
            httpPost.addHeader (new BasicHeader ("Accept", "application/json"));
            httpPost.addHeader (new BasicHeader ("Content-Type", "application/json"));

            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    httpPost.addHeader ("Cookie", cookie.getName () + "=" + cookie.getValue ());
                }
            }
            Logging.info ("Executing request: " + httpPost.getRequestLine ());
            Logging.info ("Request body: " + body);
            httpPost.setEntity (new StringEntity (body));
            result = execute (httpPost);
        } catch (Exception e) {
            Logging.info ("Error: " + e.getMessage ());
            throw new RuntimeException (e.getCause ());
        }
        return result;
    }

    private synchronized static Map <String, Object> execute (HttpUriRequest request) {
        Map <String, Object> result = new HashMap <String, Object> ();
        try {
            RequestConfig globalConfig = RequestConfig.custom ().setCookieSpec (CookieSpecs.IGNORE_COOKIES).build ();
            SSLContext sslcontext =
                    SSLContexts.custom ().loadTrustMaterial (null, new TrustSelfSignedStrategy ()).build ();
            CloseableHttpClient client =
                    HttpClients.custom ().setSslcontext (sslcontext).setDefaultRequestConfig (globalConfig).build ();
            CloseableHttpResponse response = client.execute (request);
            int status = response.getStatusLine ().getStatusCode ();
            if (status >= 300) {
                Logging.info ("Error: HTTP error code: " + status);
                throw new RuntimeException (new Exception (response.getStatusLine ().toString ()));
            }

            if (response.getEntity () != null) {
                result.put (BODY, EntityUtils.toString (response.getEntity ()));
            }

            for (Header header : response.getHeaders ("Set-Cookie")) {
                String[] c = header.getValue ().split (";")[0].split ("=");
                for (Auth a : Auth.values ())
                    if (a.name ().equals (c[0]))
                        result.put (a.name (), new BasicClientCookie (a.name (), c[1]));
            }

            response.close ();
            client.close ();
        } catch (Exception e) {
            Logging.info ("Error: " + e.getMessage ());
            throw new RuntimeException (e.getCause ());
        }
        return result;
    }
}
