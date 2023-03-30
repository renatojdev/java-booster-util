package io.rjdev.booster.util.http;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import io.rjdev.booster.util.string.StringUtil;

public class HttpUtilTest {

    static Map<String,String> headers = new HashMap<>();

    @BeforeAll
    public static void start() {
        System.out.println("=======Starting junit 5 tests========");
        // headers.put("accept","application/json");
        headers.put("accept","application/json");
    }

    @Test
    public void http_util_get_test(){
        HttpUtil hu = HttpUtil.getInstance().open("https://httpbin.org/cookies");
        hu.addHeaders(headers);
        hu.addCookie("/", "test", "v1");
        String response = hu.getResponse();
        assert(!response.isEmpty());
        assert(response.equals("{  \"cookies\": {    \"test\": \"v1\"  }}"));
    }

    @Test
    @Disabled("temprary disabled")
    public void http_util_get_cookies_test(){
        HttpUtil hu = HttpUtil.getInstance().open("https://httpbin.org/cookies/set?test=v1");
        hu.addHeaders(headers);
        Map<String, String> cks = hu.getResponseCookies();
        assert(cks.size()>0);
        assert(cks.get("test").equals("v1; Path"));
    }

    @Test
    public void test_http_post_200(){
        HttpUtil hu = HttpUtil.getInstance().open("https://httpbin.org/status/201", HttpUtil.HttpMethod.POST);
        assert !StringUtil.isNotBlank(hu.getResponse());
        assert hu.getResponseCode() == 201;
    }

    @Test
    public void test_http_put_200(){
        HttpUtil hu = HttpUtil.getInstance().open("https://httpbin.org/status/204", HttpUtil.HttpMethod.PUT);
        assert !StringUtil.isNotBlank(hu.getResponse());
        assert hu.getResponseCode() == 204;
    }

    @Test
    public void test_http_delete_200(){
        HttpUtil hu = HttpUtil.getInstance().open("https://httpbin.org/status/201", HttpUtil.HttpMethod.PUT);
        assert !StringUtil.isNotBlank(hu.getResponse());
        assert hu.getResponseCode() == 201;
    }

}
