package io.rjdev.booster.util.http;

import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class HttpUtilTest {

    @BeforeAll
    public static void start() {
        System.out.println("=======Starting junit 5 tests========");
    }

    @Test
    public void http_util_get_test(){
        HttpUtil hu = new HttpUtil("https://httpbin.org/cookies", HttpUtil.method);
        hu.addCookie("/", "test", "v1");
        String response = hu.getResponse();
        assert(!response.isEmpty());
        assert(response.equals("{  \"cookies\": {    \"test\": \"v1\"  }}"));
    }

    @Test
    public void http_util_get_cookies_test(){
        HttpUtil hu = new HttpUtil("https://httpbin.org/cookies/set/test/v1", HttpUtil.method);
        Map<String, String> cks = hu.getResponseCookies();
        assert(cks.size()>0);
        assert(cks.get("test").equals("v1; Path"));
    }

}
