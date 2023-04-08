package io.rjdev.booster.util.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.rjdev.booster.util.string.Token;

/**
 * Classe HttpUtil para recuperar o post de uma URL.
 *
 * @author Renato de Paula Eduardo Jr
 * @since 22/04/2008.
 */
public class HttpUtil {

	private static HttpUtil instance = null;
	private HttpURLConnection con;
	public static HttpMethod method = HttpMethod.GET;
	private int responseCode;
	private Map<String,String> parameters;
	private String body;

	/*
	 * Singleton.
	 */
	public static HttpUtil getInstance() {
		if(instance == null)
			instance = new HttpUtil();
		return instance;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public HttpUtil open(String url){
		return open(url, method);
	}

	public void setBody(String body){
		this.body = body;
	}

	public HttpUtil open(String url, HttpMethod method) {
		URL u;
		try {
			url = addParameters(url);
			u = new URL(url);
			con = (HttpURLConnection) u.openConnection();
			con.setRequestMethod(method.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return this;
	}

	private String addParameters(String endpoint) {

        try {
            // ADD PARAMETERS
            if(parameters!=null)
                endpoint = endpoint + "?" + ParameterStringBuilder.getParamsString(parameters);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace(System.out);
        }

		return endpoint;
    }

	public void addHeaders(Map<String,String> headers){
		if(headers == null || headers.isEmpty())
			return;
		headers.entrySet().forEach(e ->
			con.setRequestProperty(e.getKey(), e.getValue()));
		;
	}

	/**
	 * Metodo para adicionar cookie no request.
	 *
	 * @param cookiePath <code>String</code> path do cookie.
	 * @param cookieName <code>String</code> nome do cookie.
	 * @param cookieValue <code>String</code> valor do cookie.
	 */
	public void addCookie(String cookiePath, String cookieName,
			String cookieValue) {

		// Add cookie to request
		String cookie = cookieName+"="+cookieValue;
		con.addRequestProperty("Cookie", cookie);
		CookieManager cookieManager = new CookieManager();
		cookieManager.getCookieStore().add(URI.create(cookiePath), HttpCookie.parse(cookie).get(0));
		cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
		CookieHandler.setDefault(cookieManager);
	}

	public String getResponse() {
		StringBuffer content = new StringBuffer();

		try {
			// For POST | PUT only - START
			if((method.toString().equals("POST") ||
				method.toString().equals("PUT")) &&
				body != null){
					con.setDoOutput(true);
					DataOutputStream os = new DataOutputStream(con.getOutputStream());
					os.writeBytes(body);
					os.flush();
					os.close();
			}
			// For POST | PUT only - END

			responseCode = con.getResponseCode();
			if(responseCode == HttpURLConnection.HTTP_OK){
				BufferedReader in;
				in = new BufferedReader(
					new InputStreamReader(con.getInputStream()));

				String inputLine;
				while ((inputLine = in.readLine()) != null) {
					content.append(inputLine);
				}
				in.close();
				con.disconnect();
			}
		} catch (IOException e) {
			e.printStackTrace(System.out);
		}
		System.out.println("Response body: "+ content.toString());
		return content.toString();
	}

	public Map<String, String> getResponseCookies(){
		Map<String, String> cookies = new HashMap<>();
		Map<String, List<String>> headerFields = con.getHeaderFields();
		System.out.println(headerFields);
		List<String> cookiesHeader = headerFields.get("Set-Cookie");
		if (cookiesHeader != null) {
			for (String cookie : cookiesHeader) {
				// Parse cookie string
				cookies.put(Token.separate(cookie, "=")[0], Token.separate(cookie, "=")[1]);
			}
		}
		return cookies;
	}

	public enum HttpMethod {
		GET,
		POST,
		PUT,
		DEL
	}

}
