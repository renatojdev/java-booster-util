package io.rjdev.booster.util.http;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class PostTest {

    @BeforeAll
    public static void start() {
        System.out.println("=======Starting junit 5 tests========");
    }

    @Test
    public void executeTest(){
        String url = "https://dummyjson.com/posts/add";
        String body = "{\"id\":151,\"title\":\"I am in love with someone. \",\"userId\":\"5\"}";

        Map<String,String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        try {
            Post post = Post.builder()
                .endpoint(url)
                .headers(headers)
                .body(body)
                .build()
                .execute();

            assertNotNull(post);
            assertEquals(HttpURLConnection.HTTP_OK, post.getResponseCode());
            assertEquals(body, post.getResponse());

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
