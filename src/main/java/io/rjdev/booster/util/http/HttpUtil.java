package io.rjdev.booster.util.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

	private HttpURLConnection con;

	public static Method method = Method.GET;

	public HttpUtil(String url, Method method) {

		URL u;
		try {
			u = new URL(url);
			con = (HttpURLConnection) u.openConnection();
			con.setRequestMethod(method.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

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
		// con.addRequestProperty("Cookie", cookie);
		con.setRequestProperty("Cookie", cookie);
		CookieManager cookieManager = new CookieManager();
		cookieManager.getCookieStore().add(URI.create(cookiePath), HttpCookie.parse(cookie).get(0));
		cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
		CookieHandler.setDefault(cookieManager);

	}

	public String getResponse() {

		StringBuffer content = new StringBuffer();
		try {
			int status = con.getResponseCode();
			if(status == 200){
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
		List<String> cookiesHeader = headerFields.get("Set-Cookie");
		if (cookiesHeader != null) {
			for (String cookie : cookiesHeader) {
				// Parse cookie string
				cookies.put(Token.separate(cookie, "=")[0], Token.separate(cookie, "=")[1]);
			}
		}
		return cookies;
	}

	public enum Method {
		GET,
		POST,
		PUT,
		DEL
	}

}
